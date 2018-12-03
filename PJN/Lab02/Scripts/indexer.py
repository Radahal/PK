import re
import os
import math

def transformCorpseFromFile(corpseFileName):
    path = "./corpseDir/"
    if not os.path.exists(path):
        os.mkdir(path)
    corpseFile = open(corpseFileName, "r", encoding="utf-8")
    counter = 0
    for line in corpseFile:
        line_arr = line[1:-2].split("', ")
        title = re.sub('[^a-zA-ZąćęńóśżźłĄĆŃÓŚŻŹŁ_0-9 ]+', '', re.sub("[ :]","_",line_arr[0][1:])).lower()
        #print(title)
        counter+=1
        filename = path+str(counter).zfill(6)+"_"+title
        articleFile = open(filename, "w", encoding="utf-8")
        #print(filename)

        for segment in line_arr:
            segment = re.sub("\[[0-9]*\]","",re.sub('[^a-zA-ZąćęńóśżźłĄĆŃÓŚŻŹŁ0-9\[\] ]+', '', re.sub("[:]"," ",segment.replace("\\n","").replace("\\xa","").replace("\\u",""))).strip())
            while "  " in segment:
                segment = re.sub("  "," " ,segment)
            if segment != '':
                print(segment, file=articleFile)
        articleFile.close()
    corpseFile.close()

def getLemmmaDictFromList():
    listfile = "./lematy-02-UTF-8.txt"
    lemmaDict = dict()
    file = open(listfile, "r", encoding="utf-8")
    for line in file:
        line = line.split("|")
        lemmaDict[line[0]] = line[1]
    return lemmaDict


def getTermsDictFromCorpse(lemmatize=False):
    path = "./corpseDir/"
    files = os.listdir(path)
    N = len(files)
    globalDict = dict()
    if lemmatize:
        lemmaDict = getLemmmaDictFromList()
    for filename in files:
        file = open(path+filename, "r", encoding="utf-8")
        fileDict = dict()
        for line in file:
            words = line.split(" ")
            for word in words:
                word = word.replace("\n","").lower()
                if lemmatize:
                    if word in lemmaDict:
                        word = lemmaDict[word]

                if word in fileDict:
                    #globalDict
                    #slowo juz istnieje - nie dodaje go
                    #dodaje nazwe pliku jesli juz istnieje w slowniku
                    #fileDict[word][0] += 1
                    fileDict[word][0] += 1
                else:
                    fileDict[word] = [1, filename]
        for key in fileDict:
            #print(key, fileDict[key][0], fileDict[key][1])
            if key in globalDict:
                #sprawdz czy global zawiera referencje do pliku, jesli nie dodaj ja, jest tak, to juz nic nie rob

                if fileDict[key][1] in globalDict[key][1]:
                    globalDict[key][0] += fileDict[key][0]
                else:
                    globalDict[key][0] += fileDict[key][0]
                    globalDict[key][1].append(fileDict[key][0])
                    globalDict[key][2].append(fileDict[key][1])
                    #globalDict["w"] = [ilość wystąpień we wszystkich plikach(N), [n0, n1, n2...nj], [plik0, plik1, plik2]]
            else:
                globalDict[key] = [fileDict[key][0], [fileDict[key][0]], [fileDict[key][1]]]

    for key in globalDict:
        #[tf-global, tf-group by file, files where exists list, n - i (number of files where term exists) ,idf - i]
        globalDict[key].append(len(globalDict[key][2]))
        globalDict[key].append(math.log10(N/globalDict[key][3]))
    return globalDict, N

def calculateWeights(dict, N, filename):
    fileWeights = open(filename, "w", encoding="utf-8")
    for term in dict:
        j = 0
        res = ""
        for file in term[1][2]:
            tfj = term[1][1][j]
            wij = tfj * term[1][4]
            res += ","+file+'-'+str(wij)
            j += 1
        print(term[0]+res, file=fileWeights)
    fileWeights.close()

def main():
    #filename = "../final/file_pl_wikipedia_org_lvl3"
    #transformCorpseFromFile(filename)
    globalDict, N = getTermsDictFromCorpse(lemmatize=False)
    sortedGlobalDictAlfabeticly = sorted(globalDict.items(), key=lambda kv: kv[0])
    sortedGlobalDictStopWords = sorted(globalDict.items(), key=lambda kv: -kv[1][0])

    #print(sortedGlobalDictAlfabeticly)
    calculateWeights(sortedGlobalDictAlfabeticly, N,"weights_alfabeticly")
    calculateWeights(sortedGlobalDictStopWords, N, "weights_stopwords")
    print("done")

if __name__ == '__main__':
    main()

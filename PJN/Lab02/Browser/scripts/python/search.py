#!"D:\Win7\Miniconda\python.exe"

import sys
import json
import cgi


def main():
    fs = cgi.FieldStorage()
    stopwords_val = 0

    settings = {}
    for k in fs.keys():
        settings[k] = fs.getvalue(k)

    if( settings['stopwords'] == "true" ):
        stopwords_val = int(settings['stopwords_val'])

    if(settings['lemmatize'] == "true"):
        phraze = lemmatizePhraze(settings['search'])
        printJSONLemmatizedResponse(phraze, stopwords_val)
    else:
        phraze = settings['search']
        printJSONResponse(phraze, stopwords_val)
    #phraze = "wojska walczące na wschodzie"
    #printJSONResponse(phraze, 10)




def getLemmmaDictFromList():
    listfile = "./lematy-02-UTF-8.txt"
    lemmaDict = dict()
    file = open(listfile, "r", encoding="utf-8")
    for line in file:
        line = line.split("|")
        lemmaDict[line[0]] = line[1]
    return lemmaDict


def lemmatizePhraze(phraze):
    lemmaDict = getLemmmaDictFromList()
    phraze2 = ""
    for word in phraze.split(" "):
        phraze2 += lemmaDict[word]+" "
    phraze2 = phraze2[0:-1]
    return phraze2



def printJSON(dictOfFiles):
    '''sys.stdout.write("Content-Type: application/json")
    sys.stdout.write("\n")
    sys.stdout.write("\n")
    result = {}
    result["success"] = True
    #d = { "file1" : 35, "file2": 11, "file3" : 3, "file4": 1}
    result['data'] = dictOfFiles
    sys.stdout.write(json.dumps(result, indent=1))
    sys.stdout.write("\n")
    sys.stdout.close()
    '''
    print("Content-Type: application/json")
    print("\n")
    result = {}
    result["success"] = True
    result['data'] = dictOfFiles
    print(json.dumps(result, indent=1))
    print("\n")



def printJSONResponse(phraze, limit):
    filename = "weights_stopwords"
    file = open(filename,"r", encoding="utf-8")
    counter = 0
    myDict=dict()
    for line in file:
        #odciecie pierwszych LIMIT stopslow
        if(counter>limit):
            line = line.split(",")
            word = line[0]
            #w szukanej frazie bylo nasze slowo
            if(word in phraze.split(" ")):
                myDict[word] = line[1:]
        counter += 1

    sorted_items = sorted(myDict.items(), key=lambda item: len(item[1]))
    fileList = myDict[sorted_items[0][0]]

    myFinalDict = dict()
    for tmp in fileList:
        tmp = tmp.split("-")
        articleName = tmp[0]
        weight = tmp[1]
        myFinalDict[articleName] = [weight, 1]
    for word in myDict:
        if(word!=sorted_items[0][0]):
            for tmp2 in myDict[word]:
                tmp2 = tmp2.split("-")
                articleName2 = tmp2[0]
                weight2 = float(tmp2[1])
                if(articleName2 in myFinalDict):
                    weight3 = float(myFinalDict[articleName2][0])*weight2
                    myFinalDict[articleName2] = [weight3, myFinalDict[articleName2][1]+1]

    dictOfFiles = dict()
    for article in myFinalDict:
        if(myFinalDict[article][1]==len(sorted_items)):
            dictOfFiles[article]=myFinalDict[article][0]

    dictOfFilesSorted = sorted(dictOfFiles.items(), key=lambda kv: -kv[1])
    myFinalDict = dict()
    for arr in dictOfFilesSorted:
        title = arr[0]
        weight = arr[1]
        myFinalDict[title] = float(weight)/len(dictOfFiles)
    printJSON(myFinalDict)



def printJSONLemmatizedResponse(phraze, limit):
    filename = "weights_lemma_stopwords"
    file = open(filename, "r", encoding="utf-8")
    counter = 0
    myDict = dict()
    for line in file:
        # odciecie pierwszych LIMIT stopslow
        if (counter > limit):
            line = line.split(",")
            word = line[0]
            # w szukanej frazie bylo nasze slowo
            if (word in phraze.split(" ")):
                myDict[word] = line[1:]
        counter += 1

    sorted_items = sorted(myDict.items(), key=lambda item: len(item[1]))
    fileList = myDict[sorted_items[0][0]]

    myFinalDict = dict()
    for tmp in fileList:
        tmp = tmp.split("-")
        articleName = tmp[0]
        weight = tmp[1]
        myFinalDict[articleName] = [weight, 1]
    for word in myDict:
        if (word != sorted_items[0][0]):
            for tmp2 in myDict[word]:
                tmp2 = tmp2.split("-")
                articleName2 = tmp2[0]
                weight2 = float(tmp2[1])
                if (articleName2 in myFinalDict):
                    weight3 = float(myFinalDict[articleName2][0]) * weight2
                    myFinalDict[articleName2] = [weight3, myFinalDict[articleName2][1] + 1]

    dictOfFiles = dict()
    for article in myFinalDict:
        if(myFinalDict[article][1]==len(sorted_items)):
            dictOfFiles[article]=myFinalDict[article][0]

    dictOfFilesSorted = sorted(dictOfFiles.items(), key=lambda kv: -kv[1])
    myFinalDict = dict()
    for arr in dictOfFilesSorted:
        title = arr[0]
        weight = arr[1]
        myFinalDict[title] = float(weight)/len(dictOfFiles)
    printJSON(myFinalDict)


if __name__ == '__main__':
    main()








from bs4 import BeautifulSoup
from urllib.request import urlopen
import ssl
from urllib.error import *
import re
import sys
import time

def parseUrl(url):
    res = []
    try :
        context = ssl._create_unverified_context()
        html = urlopen(url, context=context).read()
        soup = BeautifulSoup(html, 'html.parser')
        content = soup.find("div", id='mw-content-text').next
        for tag in content:
            if(tag.name in ["ul"]):
                for li in tag.find_all("li"):
                    res.append(li.text)
    except AttributeError as e:
        print(e)
    except HTTPError as e:
        print(e)
    return res

def getPhrazesFromUrl(url):
    res = parseUrl(url)
    filename = "phrazes"
    file = open(filename, "w", encoding="utf-8")
    for phraze in res:
        print(phraze, file=file)
    file.close()

def defineValuesOfLetters():
    values = dict()
    values['a'] = 1
    values['b'] = 3
    values['c'] = 2
    values['d'] = 2
    values['e'] = 1
    values['f'] = 5
    values['g'] = 3
    values['h'] = 3
    values['i'] = 1
    values['j'] = 3
    values['k'] = 2
    values['l'] = 2
    values['m'] = 2
    values['n'] = 1
    values['o'] = 1
    values['p'] = 2
    values['q'] = 0
    values['r'] = 1
    values['s'] = 1
    values['t'] = 2
    values['u'] = 3
    values['w'] = 1
    values['v'] = 0
    values['x'] = 0
    values['y'] = 2
    values['z'] = 1
    values['ą'] = 5
    values['ć'] = 6
    values['ę'] = 5
    values['ł'] = 3
    values['ń'] = 7
    values['ó'] = 5
    values['ś'] = 5
    values['ż'] = 5
    values['ź'] = 9
    return values

def calculateValueOfPhraze(phraze):
    values = defineValuesOfLetters()
    value = 0
    for letter in phraze:
        value = value + int(values[letter])
    return value

def getPhrazesFromFile(filename):
    file = open(filename, "r", encoding="utf-8")
    dictPhrazes = dict()
    for line in file:
        line = re.sub('[^a-zA-ZąćęńóśżźłĄĆŃÓŚŻŹŁ ]+', '', line.lower())
        dictPhrazes[line] = [ len(re.sub('[^a-zA-ZąćęńóśżźłĄĆŃÓŚŻŹŁ]+','',line)), calculateValueOfPhraze(re.sub('[^a-zA-ZąćęńóśżźłĄĆŃÓŚŻŹŁ]+','',line)) ]

    return dictPhrazes


def saveResultToFile(myDict, filename_output):
    file = open(filename_output, "w", encoding="utf-8")
    dictPhrazesSorted = sorted(myDict.items(), key=lambda kv: -kv[1][1])
    for key in dictPhrazesSorted:
        phraze = key[0]
        phraze_len = key[1][0]
        phraze_val = key[1][1]
        print(phraze, phraze_len, phraze_val, sep=";", file=file)


def getPhrazeDictFromFile(filename):
    file = open(filename, "r", encoding="utf-8")
    myDict = dict()
    for line in file:
        line = line[:-1].split(";")
        if(len(line)==3):
            #myDict[line[0]] = {'len' : line[1], 'value': line[2]}
            myDict[line[0]] = line[2]
    file.close()
    return myDict


def getLetters(string):
    lettersDict = dict()
    for i in range(0, len(string)):
        letter=string[i]
        if letter != " ":
            if letter not in lettersDict:
                lettersDict[letter] = 1
            else:
                lettersDict[letter] += 1
    lettersDictSorted = dict()
    for key in sorted(lettersDict.keys()):
        lettersDictSorted[key] = lettersDict[key]
    return lettersDictSorted


def findBestPhraze(phrazesDict, letters):
    res = ""
    letterDict = getLetters(letters)
    lettersLen = sum(letterDict.values())
    #print(letterDict)
    for phraze in phrazesDict:
        if(len(re.sub('[^a-zA-ZąćęńóśżźłĄĆŃÓŚŻŹŁ]+', '', phraze.lower()))<=lettersLen):
            phrazeLetterDict = getLetters(phraze)

            counter = 0
            for letter in phrazeLetterDict:
                if((letter in letterDict) and (letterDict[letter]>=phrazeLetterDict[letter])):
                    counter += 1
            if(counter == len(phrazeLetterDict)):
                res = phraze
                #print(res)
                break
    return res

def main():
    #url = "https://pl.wiktionary.org/wiki/Indeks:Polski_-_Zwi%C4%85zki_frazeologiczne"
    #getPhrazesFromUrl(url)
    #filename = "phrazes"
    #filename_output = "phrazes_sorted.xls"
    #dictPhrazes = getPhrazesFromFile(filename)
    #dictPhrazesSorted = sorted(dictPhrazes.items(), key=lambda kv: -kv[1][1])
    #saveResultToFile(dictPhrazes, filename_output)
    #phraze = "szlamagośkagamaabecadło"

    '''if(len(sys.argv)!=2):
        print("Wrong number of arguments. Script needs one argument - any string (collection of letters)")
        exit(1)
    else:
        start0 = time.time()
        phraze = sys.argv[1]
        filename = "phrazes_sorted"
        phrazesDict = getPhrazeDictFromFile(filename)
        start1 = time.time()
        res = findBestPhraze(phrazesDict, phraze)
        end = time.time()
        #print("ilość liter:", sum(letterDict.values()), letterDict)
        print("Phraze: ", res)
        print("Total time:", end-start0, "[s]")
        print("Search time:", end-start1, "[s]")
        print("Collecting phrazes time:", start1-start0, "[s]")
        return res
        exit(0)
        '''

    start0 = time.time()
    #phraze = sys.argv[1]
    phraze = "szlamagośkagamaabecadło"
    filename = "phrazes_sorted"
    phrazesDict = getPhrazeDictFromFile(filename)
    start1 = time.time()
    res = findBestPhraze(phrazesDict, phraze)
    end = time.time()
    # print("ilość liter:", sum(letterDict.values()), letterDict)
    print("Phraze: ", res)
    print("Total time:", end - start0, "[s]")
    print("Search time:", end - start1, "[s]")
    print("Collecting phrazes time:", start1 - start0, "[s]")
    return res




if __name__ == '__main__':
    main()
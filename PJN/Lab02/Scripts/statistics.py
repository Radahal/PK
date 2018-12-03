
import re
import matplotlib.pyplot as plt
import numpy as np
import pandas as pd

#returns dict, ox_word, ox_uniqueWords
def getCorpseFromFile(filename):
    my_dict = dict()
    ox_words = []
    oy_uniqueWords = []
    wordCounter = 0
    file = open(filename,"r", encoding="utf-8")
    for line in file:
        line = re.sub("  "," " , re.sub("  "," " ,re.sub('[^a-zA-ZąćęńóśżźłĄĆŃÓŚŻŹŁ ]+', '', re.sub("[:]"," ",line.replace("\\n","").replace("\\xa","").replace("\\u","")))))
        for word in line.split(" "):
            if word.lower() in my_dict:
                my_dict[word.lower()] += 1

            else:
                my_dict[word.lower()] = 1
            wordCounter += 1
            ox_words.append(wordCounter)
            oy_uniqueWords.append(len(my_dict))

    return my_dict, ox_words, oy_uniqueWords


def showTopPieChart(dict, top):
    labels = []
    values = []
    explode = []
    fig1, ax1 = plt.subplots()
    for i in range(top):
        word = dict.__getitem__(i)
        labels.append(word[0])
        values.append(word[1])
        if(i<5):
            explode.append(0.1-i*0.02)
        else:
            explode.append(0)

    wedges, texts, autotext = ax1.pie(values, autopct='%1.1f%%', startangle=45, explode=explode, pctdistance=1.1, labeldistance=1.2, labels=labels)
    #ax1.legend(wedges, labels, title="Words", loc="center left")
    plt.setp(autotext, size=8)
    #ax1.set_title("TOP words")
    ax1.axis('equal')
    plt.show()


def showTopBarChart(dict, top):
    labels = []
    values = []
    fig, ax = plt.subplots()
    for i in range(top):
        word = dict.__getitem__(i)
        labels.append(word[0])
        values.append(word[1])

    freq_series = pd.Series(values)

    #plt.figure(figsize=(12, 8))
    ax = freq_series.plot(kind='bar')
    ax.set_title('TOP '+str(top)+' words')
    ax.set_xlabel('Word')
    ax.set_ylabel('Frequency')
    ax.set_xticklabels(labels)
    plt.show()

#show
def showAllPieCharts(corpseDict):
    corpseDictDesc = sorted(corpseDict.items(), key=lambda kv: -kv[1])
    showTopPieChart(corpseDictDesc, 30)
    showTopPieChart(corpseDictDesc, 100)

def showAllBarCharts(corpseDict):
    corpseDictDesc = sorted(corpseDict.items(), key=lambda kv: -kv[1])
    showTopBarChart(corpseDictDesc, 30)
    showTopBarChart(corpseDictDesc, 100)

def showVocabularyIncrease(ox_words, oy_uniqueWords):
    fig, ax = plt.subplots()
    ax.plot(ox_words, oy_uniqueWords)
    ax.set(xlabel="Words", ylabel="Unique words", title="Vocabulary increase")
    ax.grid()
    plt.show()

def showCoverageForTop(dict,top_limit, step=1):
    corpseDictDesc = sorted(dict.items(), key=lambda kv: -kv[1])
    #top1 - dict
    words = 0
    for word in corpseDictDesc:
        words += word[1]

    labels = []
    values = []
    for i in range(0,top_limit,step):
        word = corpseDictDesc.__getitem__(i)
        labels.append("TOP "+str(i+1))
        msum = 0
        for p in range(i):
            msum += corpseDictDesc.__getitem__(p)[1]
        values.append( (msum + word[1]) / words)

    fig, ax = plt.subplots()
    freq_series = pd.Series(values)*100

    # plt.figure(figsize=(12, 8))
    ax = freq_series.plot(kind='bar')
    ax.set_title('TOP words coverage')
    ax.set_xlabel('TOP limit')
    ax.set_ylabel('Coverage (%)')
    ax.set_xticklabels(labels)
    for i, v in enumerate(values):
        ax.text(i-0.34, v*100 + 0.02, str(round(v*100,2))+"%")

    plt.show()


def main():
    corpseDict, ox_words, oy_uniqueWords = getCorpseFromFile("file_pl_wikipedia_org_lvl3")
    showAllPieCharts(corpseDict)
    showAllBarCharts(corpseDict)
    showVocabularyIncrease(ox_words=ox_words,oy_uniqueWords=oy_uniqueWords)
    showCoverageForTop(corpseDict,10, step=1)
    exit(0)

if __name__ == '__main__':
    main()
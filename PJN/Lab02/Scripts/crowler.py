#program 4/5
#napisz robaka internetowego chodzącego po stronach i podstronach
#wydobywamy tekst - zapisywać do pliku
#zasadzić się na duży portal, jeden/dwa - informacyjne, naukowe, w języku polskim
#podajemy wejscie do portalu - strona glowna, parametr i zaczyna prace ściągając rekurencyjnie wszystko co się da
#żadne menu, obrazkow, zdjec - tylko interesujacy tekst
#można do bazy danych - skoro juz mamy
#format - bez znaku html, nieposzatkowany tekst, najlepiej by jeden wpis odpowiadał całemu artykułowi, bądź maksymalnie akapitom na które ten artykuł się rozkłada

# zadanie 5 - anializa statystyczna korpusu tekstowego (korpus ok 100MB), średnia długość słowa (6-10bajtów)
# analiza częstości - wykres kołowy, jakie słowa w jakim procencje występują najczęściej
# przyrost słownictwa (ox -zliczanie słow, oy - zliczanie ilości bez powtórzeń ktore przybyły)
# pokrycie tekstu - (ox - najczęsciej występujące, oy - występowanie od wartości) np 10% występujących słow to 30% tekstu itd...


#idea crowlera
# 1) wchodzisz na strone
# 2) pobierasz wszystkie linki - tworzysz z nich listę url
# 3) pobierasz wartość aktualnej strony, wywołujesz program rekurencyjnie na każdym z linków, zwiększając poziom zagłębienia oraz sprawdzając czy już na tej stronie nie byliśmy
# 4) sprawdź zakończenie programu

#parsowanie to pobranie elementu o id="content" i wyciągnięcie z niego tylko h1-h7 oraz p


import os
import re
import sys
from urllib.request import urlopen
from bs4 import BeautifulSoup
import ssl
from urllib.error import *


def findUrl(url, url_unique):
    url_list = []
    context = ssl._create_unverified_context()

    try:
        html_page = urlopen(url, context=context)
        soup = BeautifulSoup(html_page, 'html.parser')

        #.+(?<![jpg,avg,pdf])$
        for link in soup.findAll('a', attrs={'href': re.compile("^/wiki/")}):
            href = link.get('href')
            if(not(re.match("^/wiki/Plik:",href))):
                if(url_unique.count(href)==0):
                    url_list.append("https://pl.wikipedia.org"+href)
                    url_unique.append(href)
    except HTTPError as e:
        content = e.read()
        print(content)

    return url_list



def findAllUrl(url, url_unique, file):
    url_list = []
    context = ssl._create_unverified_context()

    try:
        html_page = urlopen(url, context=context)
        soup = BeautifulSoup(html_page, 'html.parser')

        #.+(?<![jpg,avg,pdf])$
        for link in soup.findAll('a', attrs={'href': re.compile("^/wiki/")}):
            href = link.get('href')
            if(not(re.match("^/wiki/Plik:",href))):
                if(url_unique.count(href)==0):
                    url_list.append("https://pl.wikipedia.org"+href)
                    url_unique.append(href)
                    print("https://pl.wikipedia.org"+href, file=file)
    except HTTPError as e:
        print(e)
    except AttributeError as e:
        print(e)

    return url_list

def parseUrl(url):
    res = []
    try :
        context = ssl._create_unverified_context()
        html = urlopen(url, context=context).read()
        soup = BeautifulSoup(html, 'html.parser')
        header = soup.find("h1", id='firstHeading')
        res.append(header.text)
        content = soup.find("div", id='mw-content-text').next
        for tag in content:
            if(tag.name in ["h1", "h2", "h3", "h4", "h5", "h6"]):
                res.append(tag.select_one('span.mw-headline').text)
            if(tag.name == "p"):
                res.append(tag.text)
    except AttributeError as e:
        print(e)
    except HTTPError as e:
        print(e)
    return res

def crowler(url, lvl, lvl_max, url_unique, file):
    if(lvl<lvl_max):
        list = findUrl(url, url_unique,file)
        print(parseUrl(url),file=file)
        for page in list:
            crowler(page, lvl+1, lvl_max, url_unique, file)

def main():
    #url = sys.argv[1]
    lvl=0
    lvl_max=3
    url_unique = []

    url = "https://pl.wikipedia.org/"

    file_name = url.replace("https","").replace("http","").replace("/","").replace(":","file_").replace(".","_") +"_lvl"+str(lvl_max)+"_url"
    file = open(file_name, "w", encoding="utf-8")

    #list = findUrl(url)
    crowler(url,lvl,lvl_max, url_unique,file)
    file.close()
    return 0

if __name__ == '__main__':
    main()
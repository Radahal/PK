# Zalożenia
# generowanie dyktanda
# wywołujemy skrypt nazwa_pliku
# plikiem zrodlowym to tekst
# z tekstu trzeba wyłapać wszystkie elementy sprawiające trudności w pisowni nie więcej niż jeden w wyrazie ( u/ó,ch/h,ż/rz )
# program zamyka tę trudność na "[h|ch]"
# jeśli występuje więcej niż jedno - losowanie rozstrzyga które z nich zamienimy
# jesli dwa argumenty to drugim jest nazwa pliku wyjsciowego

import random
import sys
import codecs
import re

difficulties = ["ż", "rz", "h", "ch", "u", "ó"]


def getTypes(letter):
    return {
        "ż": 0,
        "r": 0,
        "h": 1,
        "c": 1,
        "u": 2,
        "ó": 2
    }.get(letter, -1)


#def hasDifficulties(word):
#    positions = []
#    types = []
#    counter = 0
#    for i in range(0, len(difficulties)):
#        pos = word.find(difficulties[i])
#        if pos != -1:
#            positions.insert(counter, pos)
#            types.insert(counter, getTypes(word[pos]))
#            counter=counter+1
#    return counter, positions, types

def hasDifficulties(word):
    positions = []
    types = []
    counter = 0
    for i in range(0, len(difficulties)):
        pos = [m.start() for m in re.finditer(difficulties[i],word)]
        if(len(pos)>0):
            for k in range(0, len(pos)):
                positions.insert(counter+k, pos[k])
                types.insert(counter+k, getTypes(word[pos[0]]))
                counter=counter+1
    return counter,positions,types

def start(name):
    # otwarcie pliku
    #plik = io.open(name, 'r')
    #plik = open(name, 'r')
    result = []
    counter = 0

    plik = codecs.open(name,'r',encoding='utf-8')

    for line in plik:
        for word in line.split(" "):
            c, pos, types = hasDifficulties(word)
            if (c > 0):
                wybrany = random.randint(1, c) - 1  # zawsze 0 do len-1

                # zamiana slowa
                if (types[wybrany] == 0):
                    # problem ż/r
                    if(word[pos[wybrany]]=="r"):
                        word = word[0:pos[wybrany]] + word[pos[wybrany]:].replace( word[ pos[wybrany] : pos[wybrany]+1 ], "[ż|rz]", 1 )
                    else:
                        word = word.replace(word[pos[wybrany]], "[ż|rz]", 1)
                elif (types[wybrany] == 1):
                    # problem ch/h
                    if (word[pos[wybrany]] == "c"):
                        word = word[0:pos[wybrany]] + word[pos[wybrany]:].replace(word[pos[wybrany]: pos[wybrany] + 1], "[h|ch]", 1)
                    else:
                        word = word.replace(word[pos[wybrany]], "[h|ch]", 1)
                elif (types[wybrany] == 2):
                    # problem ó/u
                    word = word.replace(word[pos[wybrany]], "[ó|u]", 1)

            result.insert(counter,word)
            counter=counter+1
    # zamkniecie pliku
    plik.close()
    return result

def zapisz(plik, dane):
    file = codecs.open(plik, "w", "utf-8")
    for word in dane:
        file.write(word+" ")
    file.close()

def main():

    if (len(sys.argv)<2 or len(sys.argv)>3):
        print("Podano błędną ilość argumentów. Aby uruchomić skrypt wywołaj go z jednym argumentem, który będzie lokalizacją pliku. By rezultat zapisać do pliku podaj jego nazwę w drugim argumencie.\n")
        exit(2)

    nazwa_p = sys.argv[1]
    res = start(nazwa_p)

    if(len(sys.argv)==3):
        output = sys.argv[2]
        zapisz(output, res)
    else:
        print(res,end=" ")
    exit(0)

if __name__ == '__main__':
    main()

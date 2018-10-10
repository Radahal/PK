from random import *


liczba = round(uniform(1.00,100.00), 2)
i,k=0,0

while (i<10 and k!=liczba):
    k = float(input("Podaj liczbę z zakresu od 1-100\n"))
    i= i+1
    if(i<10):
        if(k<liczba):
            print("Liczba szukana jest większa")
        elif(k>liczba):
            print("Liczba szukana jest mniejsza")
        else:
            print("Gratulacje!")
            break
        print("Spróbuj ponownie\n")
    else:
        print("Niestety nie udało się trafić\n")
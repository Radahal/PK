from urllib.request import urlopen
from bs4 import BeautifulSoup
import ssl
from urllib.error import *


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

def readUrlFromFile(filename):
    file_source = open(filename, "r", encoding="utf-8")
    file_output = open(filename+"_output", "w", encoding="utf-8")
    for url in file_source:
        print(parseUrl(url), file=file_output)
    file_output.close()
    file_source.close()


def main():
    readUrlFromFile("file_pl_wikipedia_org_lvl3_url")
    exit(0)

if __name__ == '__main__':
    main()



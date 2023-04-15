import requests
from bs4 import BeautifulSoup

standings = [[] for _ in range(18)]

initial = 2004
for i in range(18):
    year = initial + i
    dest = 'https://www.transfermarkt.com/serie-a/tabelle/wettbewerb/IT1?saison_id=' + str(year)
    headers = {'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.3'}
    html_text = requests.get(dest, headers=headers).text

    soup = BeautifulSoup(html_text, 'lxml')
    tags = soup.find_all('td', class_='no-border-links hauptlink')

    for j in range(20):
        standings[i].append(tags[j].text.strip())

with open('raw-data.txt', 'w') as f:
    for l in standings:
        table = None
        for t in l:
            if table is None:
                table = t
            else:
                table += ',' + t
        f.write(table + '\n')


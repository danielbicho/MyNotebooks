import os
from textblob import TextBlob

for textfile in os.listdir('./text'):
    print(textfile)
    with open('./text/' + textfile) as f:
        text = f.read()
        t = TextBlob(text)

        print(t.tags)







import warc
import warcio
import os

from warcio.archiveiterator import ArchiveIterator




i = 0
name = 'pdf_'
for warc_name in os.listdir('./warcs'):
    with open('./warcs/' + warc_name, mode='rb') as f:
        for record in ArchiveIterator(f, arc2warc=True):
            if record.rec_type == 'warcinfo':
                print(record.raw_stream.read())

            elif record.rec_type == 'response':
                if record.http_headers.get_header('Content-Type') == 'application/pdf':
                    pdf_name = name + str(i)
                    with open(pdf_name, mode='wb') as output:
                        output.write(record.content_stream().read())
                        i += 1
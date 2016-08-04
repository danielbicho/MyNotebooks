for i in {1..100}
do
    for k in 1 2 3 4 5 6 7 8 9
    do 
	python worker_requests.py $i
        sleep 1s
    done
done

file：Book.c Contact.c
	gcc -o $@ $^
.PHONY:clean
clean:
	rm -f file

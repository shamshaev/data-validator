clean:
	make -C app clean

build:
	make -C app build

install:
	make -C app install

run-dist:
	make -C app run-dist

test:
	make -C app test

lint:
	make -C app lint

report:
	make -C app report

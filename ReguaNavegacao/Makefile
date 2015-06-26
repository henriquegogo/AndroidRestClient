setup:
	bundle
	npm install

watch:
	@node_modules/.bin/gulp

compile:
	@node_modules/.bin/gulp compileMin

run: kill-run
	@ruby runner.rb -s Puma -p 9139 -o 0.0.0.0

kill-run:
	@-ps aux | egrep "runner.rb" | egrep -v egrep | awk '{ print $$2 }' | xargs kill -9

var gulp            = require('gulp'),
    path            = require('path'),
    plugins         = require("gulp-load-plugins")(),

// Configs
    basePath    = "./",
    paths = {
        img             : basePath + 'build/img',
        css             : basePath + 'build/css',
        js              : basePath + 'build/js',
        scss            : basePath + 'src/scss',
        scripts         : basePath + 'src/scripts'
    },
    outputs = {
        js: "regua-navegacao.js"
    },
    patterns = {
        scss     : paths.scss + '/**/*.scss',
        scripts  : paths.scripts + "/*.*"
    };

// Helpers
function onError(err) {
    plugins.util.log(err);
    plugins.util.beep();
    this.emit('end');
}

function coffeeCompile(){
    return gulp.src(patterns.scripts)
        .pipe(plugins.plumber({
          errorHandler: onError
        }))
        .pipe(plugins.if(/[.]coffee$/, plugins.coffee()))
        .pipe(plugins.concat(outputs.js));
}

function compassCompile(){
    return gulp.src(patterns.scss)
        .pipe(plugins.compass({
            sass: paths.scss
        }))
        .on('error', onError);
}

// Tasks
gulp.task('compass', function() {
    return compassCompile()
        .pipe(gulp.dest(paths.css))
});

gulp.task('coffee', function() {
    return coffeeCompile()
        .pipe(gulp.dest(paths.js))
});

gulp.task('compileMinJs', function() {
    return coffeeCompile()
        .pipe(plugins.uglify())
        .pipe(gulp.dest(paths.js));
});

gulp.task('compileMinCss', function() {
    return compassCompile()
        .pipe(plugins.minifyCss({keepSpecialComments: 0}))
        .pipe(gulp.dest(paths.css));
});

gulp.task('compileMin', ['compileMinCss', 'compileMinJs']);

gulp.task('default', ['compass', 'coffee'], function () {
  gulp.watch(patterns.scss, ['compass']);
  gulp.watch(patterns.scripts, ['coffee']);
});

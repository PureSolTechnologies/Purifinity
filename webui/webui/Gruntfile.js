module.exports = function(grunt) {
	var srcDir = 'src/main/'
	grunt.loadNpmTasks('grunt-ts-1.5');
	grunt.loadNpmTasks('grunt-contrib-uglify');
	grunt.loadNpmTasks('grunt-contrib-less');
	grunt.loadNpmTasks('grunt-contrib-cssmin');
	grunt.loadNpmTasks('grunt-contrib-copy');
	grunt.loadNpmTasks('grunt-contrib-htmlmin');
	grunt.loadNpmTasks('grunt-contrib-watch');
	grunt.loadNpmTasks('grunt-tslint');
	grunt
			.initConfig({
				copy : {
					libs : {
						files : [ {
							expand : true,
							src : [ '**' ],
							cwd : 'src/main/lib',
							dest : 'target/tmp/lib/'
						}, {
							expand : true,
							src : [ '**' ],
							cwd : 'src/main/lib',
							dest : 'target/dist/lib/'
						} ]
					},
					resources : {
						files : [ {
							expand : true,
							src : [ '**' ],
							cwd : 'src/main/images/',
							dest : 'target/tmp/images/'
						}, {
							expand : true,
							src : [ '**' ],
							cwd : 'src/main/fonts/',
							dest : 'target/tmp/fonts/'
						}, {
							expand : true,
							src : [ '**' ],
							cwd : 'src/main/images/',
							dest : 'target/dist/images/'
						}, {
							expand : true,
							src : [ '**' ],
							cwd : 'src/main/fonts/',
							dest : 'target/dist/fonts/'
						} ]
					},
					html : {
						files : [ {
							expand : true,
							src : [ '**' ],
							cwd : 'src/main/html/',
							dest : 'target/tmp/'
						}, {
							expand : true,
							src : [ '**' ],
							cwd : 'src/main/html/',
							dest : 'target/dist/'
						} ]
					},
					javascript : {
						files : [ {
							expand : true,
							src : [ '**' ],
							cwd : 'src/main/javascript/',
							dest : 'target/tmp/js'
						} ]
					}
				},
				ts : {
					default : {
						src : [ 'src/main/typescript/**/*.ts' ],
						out : 'target/tmp/js/purifinity-ui.js',
						options : {
							module : 'amd',
							target : 'es5',
							rootDir : 'src/main/typescript',
							watch : false,
							sourceMap : false,
							declaration : false
						}
					}
				},
				less : {
					development : {
						files : {
							'target/tmp/css/purifinity-ui.css' : 'src/main/less/purifinity-ui.less'
						}
					},
				},
				uglify : {
					target : {
						files : {
							'target/dist/js/purifinity-ui.js' : 'target/tmp/js/purifinity-ui.js',
						}
					}
				},
				cssmin : {
					target : {
						files : {
							'target/dist/css/purifinity-ui.css' : 'target/tmp/css/purifinity-ui.css'
						}
					}
				},
				htmlmin : {
					production : {
						files : [ {
							expand : true,
							src : [ '**/*.html' ],
							cwd : 'target/tmp/',
							dest : 'target/dist/'
						} ],
						options : {
							removeComments : true,
							removeCommentsFromCDATA : true,
							removeCDATASectionsFromCDATA : true,
							collapseWhitespace : true,
							caseSensitive : true,
							minifyJS : true,
							/*
							 * Do not use removeRedundantAttributes and
							 * minifyCSS! AngularJS/Bootstrap might break!
							 */
							removeRedundantAttributes : false,
							minifyCSS : false
						}
					}
				},
				tslint : {
					options : {
						configuration : grunt.file.readJSON("tslint.json")
					},
					all : {
						src : [ "src/main/typescript/**/*.ts",
								"!src/main/typescript/lib/**/*.ts" ]
					},
				},
				watch : {
					scripts : {
						files : [ 'src/main/typescript/**/*.ts' ],
						tasks : [ 'typescript', 'uglify' ],
						options : {
							spawn : false
						}
					},
					styles : {
						files : [ 'src/main/less/**/*.less' ],
						tasks : [ 'less', 'cssmin' ],
						options : {
							spawn : false
						}
					},
					html : {
						files : [ 'src/main/html/**/*' ],
						tasks : [ 'copy:html', 'htmlmin' ],
						options : {
							spawn : false
						}
					}
				}
			});
	grunt.registerTask('default', 'Builds the whole distribution', [ 'copy',
			'htmlmin', 'ts', 'uglify', 'less', 'cssmin' ]);
}

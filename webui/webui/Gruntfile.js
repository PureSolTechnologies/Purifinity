module.exports = function(grunt) {
	grunt.loadNpmTasks('grunt-typescript');
	grunt.loadNpmTasks('grunt-contrib-uglify');
	grunt.loadNpmTasks('grunt-contrib-less');
	grunt.loadNpmTasks('grunt-contrib-cssmin');
	grunt.loadNpmTasks('grunt-contrib-watch');
	grunt.loadNpmTasks('grunt-contrib-copy');
	grunt
			.initConfig({
				copy : {
					production : {
						files : [ {
							expand : true,
							src : [ '**' ],
							cwd : 'src/main/html/',
							dest : 'target/dist/'
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
					development : {
						files : [ {
							expand : true,
							src : [ '**' ],
							cwd : 'src/html/',
							dest : 'target/tmp/'
						}, {
							expand : true,
							src : [ '**' ],
							cwd : 'src/main/images/',
							dest : 'target/tmp/images/'
						}, {
							expand : true,
							src : [ '**' ],
							cwd : 'src/main/fonts/',
							dest : 'target/tmp/fonts/'
						} ]
					}
				},
				typescript : {
					base : {
						src : [ 'src/main/typescript/**/*.ts' ],
						dest : 'target/tmp/js/purifinity-ui.js',
						options : {
							module : 'amd',
							target : 'es5',
							basePath : 'src/typescript',
							watch : false,
							sourceMap : true,
							declaration : true
						}
					}
				},
				less : {
					development : {
						files : {
							'target/tmp/css/purifinity-ui.css' : 'src/main/less/purifinity-ui.less'
						}
					},
					production : {
						files : {
							'target/tmp/css/purifinity-ui.css' : 'src/main/less/purifinity-ui.less'
						}
					}
				},
				uglify : {
					target : {
						files : {
							'target/dist/js/purifinity-ui.js' : 'target/tmp/js/purifinity-ui.js'
						}
					}
				},
				cssmin : {
					target : {
						files : {
							'target/tmp/css/purifinity-ui.css' : 'target/dist/css/purifinity-ui.css'
						}
					}
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
						files : [ 'src/main/typescript/**/*.ts' ],
						tasks : [ 'less', 'cssmin' ],
						options : {
							spawn : false
						}
					},
					html : {
						files : [ 'src/main/html/**/*' ],
						tasks : [ 'copy', 'cssmin' ],
						options : {
							spawn : false
						}
					}
				}
			});
	grunt.registerTask('default', 'Builds the whole distribution', [
			'typescript', 'uglify', 'less', 'cssmin', 'copy' ]);
}

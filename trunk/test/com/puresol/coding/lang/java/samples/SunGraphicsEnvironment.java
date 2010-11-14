package sun.java2d;

public abstract class SunGraphicsEnvironment {

	public SunGraphicsEnvironment() {
		java.security.AccessController
				.doPrivileged(new java.security.PrivilegedAction() {
					public Object run() {
						String osName = System.getProperty("os.name");
						if ("Linux".equals(osName)) {
							isLinux = true;
						} else if ("SunOS".equals(osName)) {
							isSolaris = true;
							String version = System.getProperty("os.version",
									"0.0");
							try {
								float ver = Float.parseFloat(version);
								if (ver > 5.10f) {
									File f = new File("/etc/release");
									FileInputStream fis = new FileInputStream(f);
									InputStreamReader isr = new InputStreamReader(
											fis, "ISO-8859-1");
									BufferedReader br = new BufferedReader(isr);
									String line = br.readLine();
									if (line.indexOf("OpenSolaris") >= 0) {
										isOpenSolaris = true;
									} else {
										/*
										 * "Solaris Next" (03/10) is missing the
										 * fonts expected by this release's
										 * fontconfig file. So if they aren't
										 * there, then we must also use the
										 * OpenSolaris font mapping. Look for
										 * Courier New as lack of a monospaced
										 * font is what seems to get noticed
										 * first.
										 */
										String courierNew = "/usr/openwin/lib/X11/fonts/TrueType/CourierNew.ttf";
										File courierFile = new File(courierNew);
										isOpenSolaris = !courierFile.exists();
									}
									fis.close();
								}
							} catch (Exception e) {
							}
						}

						noType1Font = "true".equals(System
								.getProperty("sun.java2d.noType1Font"));

						jreLibDirName = System.getProperty("java.home", "")
								+ File.separator + "lib";

						jreFontDirName = jreLibDirName + File.separator
								+ "fonts";

						if (useAbsoluteFontFileNames()) {
							lucidaSansFileName = jreFontDirName
									+ File.separator + "LucidaSansRegular.ttf";
						} else {
							lucidaSansFileName = "LucidaSansRegular.ttf";
						}

						File badFontFile = new File(jreFontDirName
								+ File.separator + "badfonts.txt");
						if (badFontFile.exists()) {
							FileInputStream fis = null;
							try {
								badFonts = new ArrayList();
								fis = new FileInputStream(badFontFile);
								InputStreamReader isr = new InputStreamReader(
										fis);
								BufferedReader br = new BufferedReader(isr);
								while (true) {
									String name = br.readLine();
									if (name == null) {
										break;
									} else {
										if (debugFonts) {
											logger.warning("read bad font: "
													+ name);
										}
										badFonts.add(name);
									}
								}
							} catch (IOException e) {
								try {
									if (fis != null) {
										fis.close();
									}
								} catch (IOException ioe) {
								}
							}
						}
					}
				});
	}
}

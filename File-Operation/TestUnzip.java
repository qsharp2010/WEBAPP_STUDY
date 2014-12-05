  // unzip the file to a specified folder.
  public class TestUnzip {
    public boolean unzip(String zipPath, String unzipPath) {
        ZipFile zipFile = null;
        try {
            zipFile = new ZipFile(zipPath);
            Enumeration<? extends ZipEntry> enumZip = zipFile.entries();
            while (enumZip.hasMoreElements()) {
                ZipEntry zipEntry = (ZipEntry) enumZip.nextElement();
                File unzipFile = new File(unzipPath);
                File outFile = new File(unzipFile.getAbsolutePath() + ElementsTypeDefinition.SLASH, zipEntry.getName());
                BufferedInputStream in = new BufferedInputStream(zipFile.getInputStream(zipEntry));
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(outFile));
                byte[] buffer = new byte[1024];
                int readSize = 0;
                while ((readSize = in.read(buffer)) != -1) {
                    out.write(buffer, 0, readSize);
                }
                out.close();
                in.close();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (zipFile != null) {
                try {
                    zipFile.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
  }

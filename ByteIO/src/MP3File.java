import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
* @author Ali Can Gürbüz
* finde songTitle, artist, album, year, commet and genre von einem 
* MP3 File 
*
*/

 class MP3File {

    /**
     * Attribute songTitle 
     */
    String songTitle;
    /**
     * Attribute Künstler
     */
    String artisi;
    /**
     * Attribute Album
     */
    String album;
    /**
     * Attribute Jahr
     */
    String year;
    /**
     * Attribute commet
     */
    String commet;
    /**
     * Attribute genre
     */
    String genre;

    /**
     * Konstruktor
     * @param dateiName dateiName
     * @throws IOException IOException
     */
    public MP3File(Path dateiName) throws IOException {
        try (
                InputStream in = Files.newInputStream(dateiName)
        ) {
            int bytesRead;
            byte[] bytes = inputStreamToByteArray(in);
            byte[] songTitleArr = new byte[30];
            byte[] artistArr = new byte[30];
            byte[] albumArr = new byte[30];
            byte[] yearArr = new byte[4];
            byte[] commetArr = new byte[30];
            byte genreArr = 0;
            int counter = 0;
            for (int i = bytes.length - 125; i < bytes.length - 125 + 30; i++) {
                songTitleArr[counter] = bytes[i];
                counter++;
            }
            counter = 0;
            for (int i = bytes.length - 125 + 30; i < bytes.length - 125 + 30 + 30; i++) {
                artistArr[counter] = bytes[i];
                counter++;
            }
            counter = 0;
            for (int i = bytes.length - 125 + 60; i < bytes.length - 125 + 90; i++) {
                albumArr[counter] = bytes[i];
                counter++;
            }
            counter = 0;
            for (int i = bytes.length - 125 + 90; i < bytes.length - 125 + 90 + 4; i++) {
                yearArr[counter] = bytes[i];
                counter++;
            }
            counter = 0;
            for (int i = bytes.length - 125 + 94; i < bytes.length - 125 + 124; i++) {
                commetArr[counter] = bytes[i];
                counter++;
            }
            counter = 0;
            for (int i = bytes.length-1; i < bytes.length; i++) {
                genreArr = bytes[i];
            }
            songTitle = new String(songTitleArr).trim();
            artisi = new String(artistArr).trim();
            album = new String(albumArr).trim();
            year = new String(yearArr).trim();
            commet = new String(commetArr).trim();
            genre = Byte.toString(genreArr).trim();
        }
        toString();
    }

    /**
     * Konstruktor
     * @param dateiName dateiName
     * @throws IOException IOException
     */
    public MP3File(String dateiName) throws IOException {
        try (
                InputStream in = Files.newInputStream(Paths.get(dateiName))
        ) {
            int bytesRead;
            byte[] bytes = inputStreamToByteArray(in);
            byte[] songTitleArr = new byte[30];
            byte[] artistArr = new byte[30];
            byte[] albumArr = new byte[30];
            byte[] yearArr = new byte[4];
            byte[] commetArr = new byte[30];
            byte genreArr = 0;
            byte[] endByte = new byte[128];
            int counter = 0;
            for (int i = bytes.length - 125; i < bytes.length - 125 + 30; i++) {
                songTitleArr[counter] = bytes[i];
                counter++;
            }
            counter = 0;
            for (int i = bytes.length - 125 + 30; i < bytes.length - 125 + 30 + 30; i++) {
                artistArr[counter] = bytes[i];
                counter++;
            }
            counter = 0;
            for (int i = bytes.length - 125 + 60; i < bytes.length - 125 + 90; i++) {
                albumArr[counter] = bytes[i];
                counter++;
            }
            counter = 0;
            for (int i = bytes.length - 125 + 90; i < bytes.length - 125 + 90 + 4; i++) {
                yearArr[counter] = bytes[i];
                counter++;
            }
            counter = 0;
            for (int i = bytes.length - 125 + 94; i < bytes.length - 125 + 124; i++) {
                commetArr[counter] = bytes[i];
                counter++;
            }
            counter = 0;
            for (int i = bytes.length-1; i < bytes.length; i++) {
                genreArr = bytes[i];
            }
            songTitle = new String(songTitleArr).trim();
            artisi = new String(artistArr).trim();
            album = new String(albumArr).trim();
            year = new String(yearArr).trim();
            genre = Byte.toString(genreArr).trim();
        }
        toString();
    }




    /**
     * inputStreamToByteArray wandelt InputStream in Bytes
     * @param inStream InputStream
     * @return byte[]
     * @throws IOException IOException
     */
    public byte[] inputStreamToByteArray(InputStream inStream) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[8192];
        int bytesRead;
        while ((bytesRead = inStream.read(buffer)) > 0) {
            baos.write(buffer, 0, bytesRead);
        }
        return baos.toByteArray();
    }

    /**
     * toString methode
     * @return String
     */
    @Override
    public String toString() {
        return
                songTitle + " von " + artisi + " in " + album + "(" + year + ")" + " Sprecher " + commet + ", " + genre;
    }
}

class Test {
    public static void main(String[] args) throws IOException {
        MP3File mp3File = new MP3File( "Reynmen - Dolunay (Official Audio).mp3");
        System.out.println(mp3File.toString());
    }
}

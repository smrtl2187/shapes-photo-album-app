import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;

import javax.swing.text.html.HTML;

import photoalbumapp.controller.IPhotoAlbumController;
import photoalbumapp.controller.PhotoAlbumController;
import photoalbumapp.model.IPhotoAlbumModel;
import photoalbumapp.model.PhotoAlbumModel;
import photoalbumapp.view.IPhotoAlbumView;
import photoalbumapp.view.html.HTMLView;

import static org.junit.Assert.assertEquals;

public class HTMLVIewTest {
  private IPhotoAlbumView v1;
  private IPhotoAlbumModel m1;
  private IPhotoAlbumController c1;
  private String outFileName;
  private String inFileName;

  @Before
  public void setUp() throws IOException {
    outFileName = "buildings.html";
    inFileName = "resources/buildings.txt";
    m1 = new PhotoAlbumModel();
    v1 = new HTMLView(outFileName);
    c1 = new PhotoAlbumController(m1, v1, inFileName);
  }
  @Test
  public void testHTMLViewTest() throws IOException {
    v1.generateView(m1.getSnapshotsMap());

    //actual output
    FileInputStream inputStream = new FileInputStream(outFileName);
    byte[] bytes = inputStream.readAllBytes();
    String content = new String(bytes);
    StringReader stringReader = new StringReader(content);

    //expected output
    FileInputStream inputStream2 = new FileInputStream("resources/buildingsOut.html");
    byte[] bytes2 = inputStream.readAllBytes();
    String content2 = new String(bytes);
    StringReader stringReader2 = new StringReader(content2);

    //compare
    assertEquals(content, content2);
  }

}

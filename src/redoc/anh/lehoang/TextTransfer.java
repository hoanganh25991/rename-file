package redoc.anh.lehoang;

import java.awt.*;
import java.awt.datatransfer.*;
import java.io.IOException;

public final class TextTransfer implements ClipboardOwner {

//  public static void main(){
//    TextTransfer textTransfer = new TextTransfer();
//    String lowercase_string = textTransfer.getClipboardContents().toLowerCase();
//    StringSelection stringSelection = new StringSelection(lowercase_string);
//    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
//    clipboard.setContents(stringSelection, textTransfer);
//    System.out.println(lowercase_string);
//  }
   /**
   * Empty implementation of the ClipboardOwner interface.
   */
   @Override public void lostOwnership(Clipboard aClipboard, Transferable aContents){
     //do nothing
   }

  /**
  * Place a String on the clipboard, and make this class the
  * owner of the Clipboard's contents.
  */
  public void setClipboardContents(String aString){
    StringSelection stringSelection = new StringSelection(aString);
    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
    clipboard.setContents(stringSelection, this);
  }

  /**
  * Get the String residing on the clipboard.
  *
  * @return any text found on the Clipboard; if none found, return an
  * empty String.
  */
  public String getClipboardContents() {
    String result = "";
    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
    //odd: the Object param of getContents is not currently used
    Transferable contents = clipboard.getContents(null);
    boolean hasTransferableText =
      (contents != null) &&
      contents.isDataFlavorSupported(DataFlavor.stringFlavor)
    ;
    if (hasTransferableText) {
      try {
        result = (String)contents.getTransferData(DataFlavor.stringFlavor);
      }
      catch (UnsupportedFlavorException | IOException ex){
        System.out.println(ex);
        ex.printStackTrace();
      }
    }
    return result;
  }
} 

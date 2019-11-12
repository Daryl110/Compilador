/* The following code was generated by JFlex 1.4.1 on 7/11/19 12:21 PM */

/*
 * Generated on 11/7/19 12:21 PM
 */
package eam.gui_compilador.Models;

import java.io.*;
import javax.swing.text.Segment;

import org.fife.ui.rsyntaxtextarea.*;


/**
 * 
 */

public class SolarTokenMaker extends AbstractJFlexCTokenMaker {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int EOL_COMMENT = 4;
  public static final int STRING = 1;
  public static final int YYINITIAL = 0;
  public static final int MLC = 3;
  public static final int CHAR = 2;

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\11\0\1\16\1\6\1\0\1\16\1\14\22\0\1\16\1\65\1\13"+
    "\1\15\1\1\1\37\1\35\1\5\2\34\1\20\1\36\1\33\1\23"+
    "\1\21\1\17\1\3\3\3\4\3\2\3\1\43\1\33\1\67\1\66"+
    "\1\45\1\33\1\15\1\53\1\60\1\4\1\61\2\4\7\1\1\46"+
    "\4\1\1\64\7\1\1\34\1\7\1\34\1\70\1\2\1\0\1\27"+
    "\1\12\1\50\1\52\1\22\1\26\1\56\1\40\1\42\1\1\1\47"+
    "\1\30\1\55\1\11\1\51\1\41\1\1\1\25\1\31\1\24\1\10"+
    "\1\62\1\44\1\57\1\54\1\63\1\32\1\71\1\32\1\33\uff81\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\1\0\2\1\2\0\2\2\1\3\1\4\1\5\3\2"+
    "\1\6\1\7\2\10\2\2\1\10\5\2\1\11\1\2"+
    "\2\10\1\2\1\10\7\2\1\1\1\12\1\1\1\13"+
    "\1\1\1\14\5\1\1\15\3\1\1\0\1\16\1\0"+
    "\1\16\4\2\1\17\1\20\1\21\11\0\1\2\1\22"+
    "\10\2\1\22\6\2\1\23\11\0\1\21\1\0\5\2"+
    "\15\0\5\2\1\24\7\2\11\0\3\2\3\0\1\25"+
    "\10\0\1\26\7\2\2\0\1\27\2\0\1\30\1\0"+
    "\2\2\11\0\7\2\5\0\1\2\7\0\4\2\22\0";

  private static int [] zzUnpackAction() {
    int [] result = new int[225];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\72\0\164\0\256\0\350\0\u0122\0\u015c\0\u0196"+
    "\0\u0122\0\u0122\0\u01d0\0\u020a\0\u0244\0\u0122\0\u027e\0\u02b8"+
    "\0\u02f2\0\u032c\0\u0366\0\u03a0\0\u03da\0\u0414\0\u044e\0\u0488"+
    "\0\u04c2\0\u0122\0\u04fc\0\u0536\0\u0570\0\u05aa\0\u0122\0\u05e4"+
    "\0\u061e\0\u0658\0\u0692\0\u06cc\0\u0706\0\u0740\0\u077a\0\u0122"+
    "\0\u07b4\0\u0122\0\u07ee\0\u0122\0\u0828\0\u0862\0\u089c\0\u08d6"+
    "\0\u0910\0\u0122\0\u094a\0\u0984\0\u09be\0\u09f8\0\u0a32\0\u0a6c"+
    "\0\u0aa6\0\u0ae0\0\u0b1a\0\u0b54\0\u0b8e\0\u0122\0\u0122\0\u0bc8"+
    "\0\u0c02\0\u0c3c\0\u0c76\0\u0cb0\0\u0cea\0\u0d24\0\u0d5e\0\u0d98"+
    "\0\u0dd2\0\u0e0c\0\u0122\0\u0e46\0\u0e80\0\u0eba\0\u0ef4\0\u0f2e"+
    "\0\u0f68\0\u0fa2\0\u0fdc\0\u015c\0\u1016\0\u1050\0\u108a\0\u10c4"+
    "\0\u10fe\0\u1138\0\u0122\0\u1172\0\u11ac\0\u11e6\0\u1220\0\u125a"+
    "\0\u1294\0\u12ce\0\u1308\0\u1342\0\u137c\0\u13b6\0\u13f0\0\u142a"+
    "\0\u1464\0\u149e\0\u14d8\0\u1512\0\u154c\0\u1586\0\u15c0\0\u15fa"+
    "\0\u1634\0\u166e\0\u16a8\0\u16e2\0\u171c\0\u1756\0\u1790\0\u17ca"+
    "\0\u1804\0\u183e\0\u1878\0\u18b2\0\u18ec\0\u015c\0\u1926\0\u1960"+
    "\0\u199a\0\u19d4\0\u1a0e\0\u1a48\0\u1a82\0\u1abc\0\u1af6\0\u1b30"+
    "\0\u1b6a\0\u1ba4\0\u1bde\0\u1c18\0\u1c52\0\u1c8c\0\u1cc6\0\u1d00"+
    "\0\u1d3a\0\u1d74\0\u1dae\0\u1de8\0\u0122\0\u1e22\0\u1e5c\0\u1e96"+
    "\0\u1ed0\0\u1f0a\0\u1f44\0\u1f7e\0\u1fb8\0\u015c\0\u1ff2\0\u202c"+
    "\0\u2066\0\u20a0\0\u20da\0\u2114\0\u214e\0\u2188\0\u21c2\0\u21fc"+
    "\0\u2236\0\u2270\0\u22aa\0\u22e4\0\u231e\0\u2358\0\u2392\0\u23cc"+
    "\0\u2406\0\u2440\0\u247a\0\u24b4\0\u24ee\0\u2528\0\u2562\0\u259c"+
    "\0\u25d6\0\u2610\0\u264a\0\u2684\0\u26be\0\u26f8\0\u2732\0\u21fc"+
    "\0\u276c\0\u22aa\0\u27a6\0\u27e0\0\u281a\0\u2854\0\u288e\0\u28c8"+
    "\0\u2902\0\u293c\0\u2976\0\u29b0\0\u29ea\0\u2a24\0\u2a5e\0\u2a98"+
    "\0\u2ad2\0\u2b0c\0\u2b46\0\u2b80\0\u2bba\0\u2bf4\0\u2c2e\0\u2c68"+
    "\0\u2ca2\0\u2cdc\0\u2d16\0\u2d50\0\u2d8a\0\u2dc4\0\u2dfe\0\u2e38"+
    "\0\u2e72";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[225];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\6\2\7\1\10\1\7\1\11\1\12\1\6\1\13"+
    "\1\14\1\15\1\16\2\6\1\17\1\20\1\21\1\22"+
    "\1\23\1\24\1\25\1\26\1\27\1\30\1\7\1\31"+
    "\1\32\1\6\1\32\1\33\1\34\1\35\2\7\1\36"+
    "\1\37\1\40\1\35\1\41\1\7\1\42\1\7\1\43"+
    "\1\44\10\7\1\45\1\37\2\35\1\37\1\46\13\47"+
    "\1\50\56\47\5\51\1\52\64\51\6\53\1\54\11\53"+
    "\1\55\5\53\1\56\11\53\1\57\3\53\1\60\25\53"+
    "\6\61\1\62\17\61\1\63\11\61\1\64\3\61\1\65"+
    "\25\61\73\0\4\7\2\0\1\66\3\7\7\0\1\7"+
    "\1\0\6\7\6\0\3\7\1\0\1\7\1\0\17\7"+
    "\5\0\3\67\1\10\1\67\2\0\4\67\2\0\1\67"+
    "\3\0\1\70\1\71\1\0\6\67\6\0\3\67\1\0"+
    "\1\67\1\0\17\67\6\0\4\7\2\0\1\66\1\7"+
    "\1\72\1\7\7\0\1\7\1\0\6\7\6\0\3\7"+
    "\1\0\1\7\1\0\17\7\6\0\4\7\2\0\1\66"+
    "\1\73\2\7\7\0\1\7\1\0\6\7\6\0\3\7"+
    "\1\0\1\7\1\0\17\7\6\0\4\7\2\0\1\66"+
    "\3\7\7\0\1\7\1\0\1\7\1\74\4\7\6\0"+
    "\3\7\1\0\1\7\1\0\3\7\1\75\13\7\23\0"+
    "\1\17\72\0\1\76\1\77\45\0\1\37\23\0\1\37"+
    "\45\0\1\37\6\0\1\100\4\0\1\101\14\0\1\102"+
    "\1\0\1\103\1\0\1\104\7\0\1\105\1\106\6\0"+
    "\1\107\1\110\2\0\1\111\15\0\4\7\2\0\1\66"+
    "\3\7\7\0\1\7\1\0\4\7\1\112\1\7\6\0"+
    "\3\7\1\0\1\7\1\0\17\7\30\0\1\37\21\0"+
    "\1\113\20\0\1\37\4\0\4\7\2\0\1\66\3\7"+
    "\7\0\1\7\1\0\1\7\1\114\4\7\6\0\3\7"+
    "\1\0\1\7\1\0\17\7\6\0\4\7\2\0\1\66"+
    "\3\7\7\0\1\115\1\0\6\7\6\0\3\7\1\0"+
    "\1\7\1\0\17\7\6\0\4\7\2\0\1\66\1\116"+
    "\2\7\7\0\1\7\1\0\3\7\1\117\2\7\6\0"+
    "\3\7\1\0\1\7\1\0\3\7\1\120\13\7\6\0"+
    "\4\7\2\0\1\66\1\7\1\121\1\7\7\0\1\7"+
    "\1\0\6\7\6\0\3\7\1\0\1\7\1\0\17\7"+
    "\6\0\4\7\2\0\1\66\3\7\7\0\1\7\1\0"+
    "\1\122\5\7\6\0\3\7\1\0\1\123\1\0\17\7"+
    "\42\0\1\37\72\0\1\37\27\0\1\37\71\0\1\37"+
    "\4\0\4\7\2\0\1\66\3\7\7\0\1\7\1\0"+
    "\2\7\1\124\3\7\6\0\3\7\1\0\1\7\1\0"+
    "\17\7\6\0\4\7\2\0\1\66\3\7\7\0\1\7"+
    "\1\0\6\7\6\0\1\125\2\7\1\0\1\7\1\0"+
    "\17\7\6\0\4\7\2\0\1\66\3\7\7\0\1\7"+
    "\1\0\3\7\1\126\2\7\6\0\3\7\1\0\1\7"+
    "\1\0\17\7\6\0\4\7\2\0\1\66\3\7\7\0"+
    "\1\7\1\0\3\7\1\112\2\7\6\0\3\7\1\0"+
    "\1\7\1\0\3\7\1\127\13\7\6\0\4\7\2\0"+
    "\1\66\3\7\7\0\1\130\1\0\6\7\6\0\3\7"+
    "\1\0\1\7\1\0\17\7\6\0\4\7\2\0\1\66"+
    "\3\7\7\0\1\7\1\0\1\7\1\131\4\7\6\0"+
    "\3\7\1\0\1\7\1\0\17\7\6\0\4\7\2\0"+
    "\1\66\3\7\7\0\1\7\1\0\6\7\6\0\3\7"+
    "\1\0\1\7\1\0\6\7\1\132\10\7\76\0\1\37"+
    "\13\47\1\0\56\47\5\51\1\0\64\51\6\53\1\0"+
    "\11\53\1\0\5\53\1\0\11\53\1\0\3\53\1\0"+
    "\25\53\17\0\1\133\76\0\1\134\15\0\1\135\53\0"+
    "\1\136\111\0\1\137\25\0\6\61\1\0\17\61\1\0"+
    "\11\61\1\0\3\61\1\0\25\61\24\0\1\140\15\0"+
    "\1\141\53\0\1\142\111\0\1\143\35\0\1\144\61\0"+
    "\5\67\2\0\4\67\2\0\1\67\4\0\1\67\1\0"+
    "\6\67\6\0\3\67\1\0\1\67\1\0\17\67\10\0"+
    "\1\100\66\0\3\67\1\145\1\67\2\0\4\67\2\0"+
    "\1\67\4\0\1\67\1\146\6\67\4\0\1\146\1\0"+
    "\3\67\1\0\1\67\1\0\17\67\6\0\4\7\2\0"+
    "\1\66\3\7\7\0\1\7\1\0\1\147\5\7\6\0"+
    "\3\7\1\0\1\7\1\0\17\7\6\0\4\7\2\0"+
    "\1\66\3\7\7\0\1\7\1\0\4\7\1\150\1\7"+
    "\6\0\3\7\1\0\1\7\1\0\7\7\1\151\7\7"+
    "\6\0\4\7\2\0\1\66\3\7\7\0\1\152\1\0"+
    "\6\7\6\0\3\7\1\0\1\7\1\0\17\7\6\0"+
    "\4\7\2\0\1\66\3\7\7\0\1\7\1\0\6\7"+
    "\6\0\3\7\1\0\1\7\1\0\3\7\1\153\13\7"+
    "\5\0\3\67\1\100\1\67\2\0\4\67\2\0\1\67"+
    "\4\0\1\71\1\0\6\67\6\0\3\67\1\0\1\67"+
    "\1\0\17\67\16\0\1\154\102\0\1\155\121\0\1\156"+
    "\27\0\1\157\13\0\1\160\3\0\1\161\11\0\1\162"+
    "\100\0\1\163\31\0\1\164\105\0\1\165\54\0\1\166"+
    "\110\0\1\167\12\0\1\170\30\0\4\7\2\0\1\66"+
    "\3\7\7\0\1\7\1\0\5\7\1\171\6\0\3\7"+
    "\1\0\1\7\1\0\17\7\6\0\4\7\2\0\1\66"+
    "\1\172\2\7\7\0\1\7\1\0\6\7\6\0\3\7"+
    "\1\0\1\7\1\0\17\7\6\0\4\7\2\0\1\66"+
    "\3\7\7\0\1\7\1\0\1\173\5\7\6\0\3\7"+
    "\1\0\1\7\1\0\17\7\6\0\4\7\2\0\1\66"+
    "\1\7\1\174\1\7\7\0\1\7\1\0\6\7\6\0"+
    "\3\7\1\0\1\7\1\0\17\7\6\0\4\7\2\0"+
    "\1\66\3\7\7\0\1\7\1\0\4\7\1\175\1\7"+
    "\6\0\3\7\1\0\1\7\1\0\17\7\6\0\4\7"+
    "\2\0\1\66\3\7\7\0\1\7\1\0\1\7\1\124"+
    "\4\7\6\0\3\7\1\0\1\7\1\0\17\7\6\0"+
    "\4\7\2\0\1\66\3\7\7\0\1\7\1\0\6\7"+
    "\6\0\3\7\1\0\1\7\1\0\6\7\1\176\10\7"+
    "\6\0\4\7\2\0\1\66\3\7\7\0\1\7\1\0"+
    "\1\7\1\177\4\7\6\0\3\7\1\0\1\7\1\0"+
    "\17\7\6\0\4\7\2\0\1\66\3\7\7\0\1\7"+
    "\1\0\6\7\6\0\2\7\1\200\1\0\1\7\1\0"+
    "\17\7\6\0\4\7\2\0\1\66\3\7\7\0\1\7"+
    "\1\0\6\7\6\0\2\7\1\201\1\0\1\7\1\0"+
    "\17\7\6\0\4\7\2\0\1\66\3\7\7\0\1\7"+
    "\1\0\6\7\6\0\3\7\1\0\1\7\1\0\1\124"+
    "\16\7\6\0\4\7\2\0\1\66\1\7\1\202\1\7"+
    "\7\0\1\7\1\0\6\7\6\0\3\7\1\0\1\7"+
    "\1\0\17\7\6\0\4\7\2\0\1\66\3\7\7\0"+
    "\1\7\1\0\2\7\1\203\3\7\6\0\3\7\1\0"+
    "\1\7\1\0\17\7\6\0\4\7\2\0\1\66\3\7"+
    "\7\0\1\7\1\0\1\7\1\204\4\7\6\0\3\7"+
    "\1\0\1\7\1\0\17\7\6\0\4\7\2\0\1\66"+
    "\3\7\7\0\1\7\1\0\5\7\1\205\6\0\3\7"+
    "\1\0\1\7\1\0\17\7\46\0\1\206\60\0\1\207"+
    "\65\0\1\210\111\0\1\211\66\0\1\212\60\0\1\213"+
    "\65\0\1\214\111\0\1\215\30\0\2\216\5\0\1\216"+
    "\7\0\1\216\3\0\2\216\20\0\1\216\1\0\2\216"+
    "\4\0\2\216\10\0\3\67\1\145\1\67\2\0\4\67"+
    "\2\0\1\67\4\0\1\67\1\0\6\67\6\0\3\67"+
    "\1\0\1\67\1\0\17\67\10\0\1\145\67\0\4\7"+
    "\2\0\1\66\3\7\7\0\1\7\1\0\6\7\6\0"+
    "\2\7\1\150\1\0\1\7\1\0\17\7\6\0\4\7"+
    "\2\0\1\66\3\7\7\0\1\7\1\0\4\7\1\124"+
    "\1\7\6\0\3\7\1\0\1\7\1\0\17\7\6\0"+
    "\4\7\2\0\1\66\2\7\1\217\7\0\1\7\1\0"+
    "\6\7\6\0\3\7\1\0\1\7\1\0\17\7\6\0"+
    "\4\7\2\0\1\66\3\7\7\0\1\7\1\0\3\7"+
    "\1\220\2\7\6\0\3\7\1\0\1\7\1\0\17\7"+
    "\6\0\4\7\2\0\1\66\3\7\7\0\1\7\1\0"+
    "\4\7\1\221\1\7\6\0\3\7\1\0\1\7\1\0"+
    "\17\7\47\0\1\222\104\0\1\223\4\0\1\224\61\0"+
    "\1\225\31\0\1\226\42\0\1\225\41\0\1\227\106\0"+
    "\1\230\112\0\1\231\36\0\1\232\10\0\1\225\2\0"+
    "\1\225\51\0\1\233\117\0\1\234\60\0\1\235\107\0"+
    "\1\225\23\0\1\225\61\0\4\7\2\0\1\66\3\7"+
    "\7\0\1\124\1\0\6\7\6\0\3\7\1\0\1\7"+
    "\1\0\17\7\6\0\4\7\2\0\1\66\3\7\7\0"+
    "\1\236\1\0\6\7\6\0\3\7\1\0\1\7\1\0"+
    "\17\7\6\0\4\7\2\0\1\66\1\237\2\7\7\0"+
    "\1\7\1\0\6\7\6\0\3\7\1\0\1\7\1\0"+
    "\17\7\6\0\4\7\2\0\1\66\3\7\7\0\1\7"+
    "\1\0\6\7\6\0\3\7\1\0\1\7\1\0\2\7"+
    "\1\240\14\7\6\0\4\7\2\0\1\66\3\7\7\0"+
    "\1\7\1\0\5\7\1\172\6\0\3\7\1\0\1\7"+
    "\1\0\17\7\6\0\4\7\2\0\1\66\3\7\7\0"+
    "\1\7\1\0\6\7\6\0\2\7\1\241\1\0\1\7"+
    "\1\0\17\7\6\0\4\7\2\0\1\66\3\7\7\0"+
    "\1\7\1\0\1\242\5\7\6\0\3\7\1\0\1\7"+
    "\1\0\17\7\6\0\4\7\2\0\1\66\3\7\7\0"+
    "\1\7\1\0\4\7\1\171\1\7\6\0\3\7\1\0"+
    "\1\7\1\0\17\7\6\0\4\7\2\0\1\66\3\7"+
    "\7\0\1\7\1\0\1\243\5\7\6\0\3\7\1\0"+
    "\1\7\1\0\17\7\6\0\4\7\2\0\1\66\3\7"+
    "\7\0\1\7\1\0\3\7\1\244\2\7\6\0\3\7"+
    "\1\0\1\7\1\0\17\7\6\0\4\7\2\0\1\66"+
    "\3\7\7\0\1\7\1\0\3\7\1\121\2\7\6\0"+
    "\3\7\1\0\1\7\1\0\17\7\6\0\4\7\2\0"+
    "\1\66\3\7\7\0\1\7\1\0\1\245\5\7\6\0"+
    "\3\7\1\0\1\7\1\0\17\7\50\0\1\246\50\0"+
    "\1\206\110\0\1\247\51\0\1\250\113\0\1\251\50\0"+
    "\1\212\110\0\1\252\51\0\1\253\53\0\2\254\5\0"+
    "\1\254\7\0\1\254\3\0\2\254\20\0\1\254\1\0"+
    "\2\254\4\0\2\254\11\0\4\7\2\0\1\66\3\7"+
    "\7\0\1\255\1\0\6\7\6\0\3\7\1\0\1\7"+
    "\1\0\17\7\6\0\4\7\2\0\1\66\3\7\7\0"+
    "\1\7\1\0\6\7\6\0\3\7\1\0\1\7\1\0"+
    "\1\7\1\124\15\7\6\0\4\7\2\0\1\66\3\7"+
    "\7\0\1\256\1\0\6\7\6\0\3\7\1\0\1\7"+
    "\1\0\17\7\56\0\1\170\71\0\1\257\42\0\1\260"+
    "\100\0\1\261\67\0\1\262\112\0\1\263\43\0\1\225"+
    "\111\0\1\264\51\0\1\265\71\0\1\266\77\0\1\267"+
    "\42\0\4\7\2\0\1\66\3\7\7\0\1\7\1\0"+
    "\1\7\1\270\4\7\6\0\3\7\1\0\1\7\1\0"+
    "\17\7\6\0\4\7\2\0\1\66\3\7\7\0\1\7"+
    "\1\0\1\271\5\7\6\0\3\7\1\0\1\7\1\0"+
    "\17\7\6\0\4\7\2\0\1\66\1\7\1\272\1\7"+
    "\7\0\1\7\1\0\6\7\6\0\3\7\1\0\1\7"+
    "\1\0\17\7\6\0\4\7\2\0\1\66\3\7\7\0"+
    "\1\7\1\0\6\7\6\0\3\7\1\0\1\7\1\0"+
    "\2\7\1\273\14\7\6\0\4\7\2\0\1\66\3\7"+
    "\7\0\1\7\1\0\6\7\6\0\2\7\1\274\1\0"+
    "\1\7\1\0\17\7\6\0\4\7\2\0\1\66\1\275"+
    "\2\7\7\0\1\7\1\0\6\7\6\0\3\7\1\0"+
    "\1\7\1\0\17\7\6\0\4\7\2\0\1\66\3\7"+
    "\7\0\1\276\1\0\6\7\6\0\3\7\1\0\1\7"+
    "\1\0\17\7\24\0\1\277\103\0\1\206\11\0\1\246"+
    "\27\0\1\250\1\300\2\250\1\300\2\0\3\250\2\0"+
    "\1\300\1\0\1\250\2\300\1\250\1\300\6\250\1\0"+
    "\5\300\3\250\1\300\1\250\1\0\17\250\2\300\22\0"+
    "\1\301\103\0\1\212\11\0\1\251\27\0\1\253\1\302"+
    "\2\253\1\302\2\0\3\253\2\0\1\302\1\0\1\253"+
    "\2\302\1\253\1\302\6\253\1\0\5\302\3\253\1\302"+
    "\1\253\1\0\17\253\2\302\6\0\2\303\5\0\1\303"+
    "\7\0\1\303\3\0\2\303\20\0\1\303\1\0\2\303"+
    "\4\0\2\303\11\0\4\7\2\0\1\66\3\7\7\0"+
    "\1\7\1\0\1\7\1\176\4\7\6\0\3\7\1\0"+
    "\1\7\1\0\17\7\6\0\4\7\2\0\1\66\3\7"+
    "\7\0\1\7\1\0\3\7\1\304\2\7\6\0\3\7"+
    "\1\0\1\7\1\0\17\7\67\0\1\231\34\0\1\305"+
    "\70\0\1\306\107\0\1\170\71\0\1\307\55\0\1\310"+
    "\70\0\1\311\71\0\1\312\106\0\1\313\30\0\4\7"+
    "\2\0\1\66\1\7\1\124\1\7\7\0\1\7\1\0"+
    "\6\7\6\0\3\7\1\0\1\7\1\0\17\7\6\0"+
    "\4\7\2\0\1\66\3\7\7\0\1\7\1\0\6\7"+
    "\6\0\2\7\1\314\1\0\1\7\1\0\17\7\6\0"+
    "\4\7\2\0\1\66\3\7\7\0\1\7\1\0\6\7"+
    "\6\0\3\7\1\0\1\7\1\0\10\7\1\176\6\7"+
    "\6\0\4\7\2\0\1\66\3\7\7\0\1\7\1\0"+
    "\6\7\6\0\1\124\2\7\1\0\1\7\1\0\17\7"+
    "\6\0\4\7\2\0\1\66\1\7\1\315\1\7\7\0"+
    "\1\7\1\0\6\7\6\0\3\7\1\0\1\7\1\0"+
    "\17\7\6\0\4\7\2\0\1\66\3\7\7\0\1\7"+
    "\1\0\4\7\1\316\1\7\6\0\3\7\1\0\1\7"+
    "\1\0\17\7\6\0\4\7\2\0\1\66\3\7\7\0"+
    "\1\7\1\0\6\7\6\0\3\7\1\0\1\7\1\0"+
    "\7\7\1\317\7\7\24\0\1\250\71\0\1\253\55\0"+
    "\2\7\5\0\1\7\7\0\1\7\3\0\2\7\20\0"+
    "\1\7\1\0\2\7\4\0\2\7\11\0\4\7\2\0"+
    "\1\66\1\7\1\176\1\7\7\0\1\7\1\0\6\7"+
    "\6\0\3\7\1\0\1\7\1\0\17\7\36\0\1\231"+
    "\65\0\1\320\55\0\1\321\102\0\1\322\100\0\1\323"+
    "\120\0\1\324\61\0\1\325\22\0\4\7\2\0\1\66"+
    "\3\7\7\0\1\7\1\0\6\7\6\0\3\7\1\0"+
    "\1\7\1\0\3\7\1\270\13\7\6\0\4\7\2\0"+
    "\1\66\1\171\2\7\7\0\1\7\1\0\6\7\6\0"+
    "\3\7\1\0\1\7\1\0\17\7\6\0\4\7\2\0"+
    "\1\66\3\7\7\0\1\7\1\0\1\124\5\7\6\0"+
    "\3\7\1\0\1\7\1\0\17\7\6\0\4\7\2\0"+
    "\1\66\3\7\6\0\1\326\1\7\1\0\6\7\6\0"+
    "\3\7\1\0\1\7\1\0\17\7\34\0\1\327\120\0"+
    "\1\225\40\0\1\330\66\0\1\331\123\0\1\332\44\0"+
    "\1\333\72\0\1\334\111\0\1\335\50\0\1\336\112\0"+
    "\1\336\74\0\1\337\5\0\1\340\34\0\1\231\116\0"+
    "\1\321\44\0\1\225\71\0\1\154\76\0\1\341\62\0"+
    "\1\337\117\0\1\225\21\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[11948];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unkown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\1\0\2\1\2\0\1\11\2\1\2\11\3\1\1\11"+
    "\13\1\1\11\4\1\1\11\10\1\1\11\1\1\1\11"+
    "\1\1\1\11\5\1\1\11\3\1\1\0\1\1\1\0"+
    "\5\1\2\11\1\1\11\0\1\1\1\11\17\1\1\11"+
    "\11\0\1\1\1\0\5\1\15\0\15\1\11\0\3\1"+
    "\3\0\1\11\10\0\10\1\2\0\1\1\2\0\1\1"+
    "\1\0\2\1\11\0\7\1\5\0\1\1\7\0\4\1"+
    "\22\0";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[225];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private char zzBuffer[];

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the textposition at the last state to be included in yytext */
  private int zzPushbackPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** number of newlines encountered up to the start of the matched text */
  private int yyline;

  /** the number of characters up to the start of the matched text */
  private int yychar;

  /**
   * the number of characters from the last newline up to the start of the 
   * matched text
   */
  private int yycolumn;

  /** 
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /* user code: */


	/**
	 * Constructor.  This must be here because JFlex does not generate a
	 * no-parameter constructor.
	 */
	public SolarTokenMaker() {
	}


	/**
	 * Adds the token specified to the current linked list of tokens.
	 *
	 * @param tokenType The token's type.
	 * @see #addToken(int, int, int)
	 */
	private void addHyperlinkToken(int start, int end, int tokenType) {
		int so = start + offsetShift;
		addToken(zzBuffer, start,end, tokenType, so, true);
	}


	/**
	 * Adds the token specified to the current linked list of tokens.
	 *
	 * @param tokenType The token's type.
	 */
	private void addToken(int tokenType) {
		addToken(zzStartRead, zzMarkedPos-1, tokenType);
	}


	/**
	 * Adds the token specified to the current linked list of tokens.
	 *
	 * @param tokenType The token's type.
	 * @see #addHyperlinkToken(int, int, int)
	 */
	private void addToken(int start, int end, int tokenType) {
		int so = start + offsetShift;
		addToken(zzBuffer, start,end, tokenType, so, false);
	}


	/**
	 * Adds the token specified to the current linked list of tokens.
	 *
	 * @param array The character array.
	 * @param start The starting offset in the array.
	 * @param end The ending offset in the array.
	 * @param tokenType The token's type.
	 * @param startOffset The offset in the document at which this token
	 *        occurs.
	 * @param hyperlink Whether this token is a hyperlink.
	 */
	public void addToken(char[] array, int start, int end, int tokenType,
						int startOffset, boolean hyperlink) {
		super.addToken(array, start,end, tokenType, startOffset, hyperlink);
		zzStartRead = zzMarkedPos;
	}


	/**
	 * {@inheritDoc}
	 */
	public String[] getLineCommentStartAndEnd(int languageIndex) {
		return new String[] { "//", null };
	}


	/**
	 * Returns the first token in the linked list of tokens generated
	 * from <code>text</code>.  This method must be implemented by
	 * subclasses so they can correctly implement syntax highlighting.
	 *
	 * @param text The text from which to get tokens.
	 * @param initialTokenType The token type we should start with.
	 * @param startOffset The offset into the document at which
	 *        <code>text</code> starts.
	 * @return The first <code>Token</code> in a linked list representing
	 *         the syntax highlighted text.
	 */
	public Token getTokenList(Segment text, int initialTokenType, int startOffset) {

		resetTokenList();
		this.offsetShift = -text.offset + startOffset;

		// Start off in the proper state.
		int state = Token.NULL;
		switch (initialTokenType) {
						case Token.COMMENT_MULTILINE:
				state = MLC;
				start = text.offset;
				break;

			/* No documentation comments */
			default:
				state = Token.NULL;
		}

		s = text;
		try {
			yyreset(zzReader);
			yybegin(state);
			return yylex();
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return new TokenImpl();
		}

	}


	/**
	 * Refills the input buffer.
	 *
	 * @return      <code>true</code> if EOF was reached, otherwise
	 *              <code>false</code>.
	 */
	private boolean zzRefill() {
		return zzCurrentPos>=s.offset+s.count;
	}


	/**
	 * Resets the scanner to read from a new input stream.
	 * Does not close the old reader.
	 *
	 * All internal variables are reset, the old input stream 
	 * <b>cannot</b> be reused (internal buffer is discarded and lost).
	 * Lexical state is set to <tt>YY_INITIAL</tt>.
	 *
	 * @param reader   the new input stream 
	 */
	public final void yyreset(Reader reader) {
		// 's' has been updated.
		zzBuffer = s.array;
		/*
		 * We replaced the line below with the two below it because zzRefill
		 * no longer "refills" the buffer (since the way we do it, it's always
		 * "full" the first time through, since it points to the segment's
		 * array).  So, we assign zzEndRead here.
		 */
		//zzStartRead = zzEndRead = s.offset;
		zzStartRead = s.offset;
		zzEndRead = zzStartRead + s.count - 1;
		zzCurrentPos = zzMarkedPos = zzPushbackPos = s.offset;
		zzLexicalState = YYINITIAL;
		zzReader = reader;
		zzAtBOL  = true;
		zzAtEOF  = false;
	}




  /**
   * Creates a new scanner
   * There is also a java.io.InputStream version of this constructor.
   *
   * @param   in  the java.io.Reader to read input from.
   */
  public SolarTokenMaker(java.io.Reader in) {
    this.zzReader = in;
  }

  /**
   * Creates a new scanner.
   * There is also java.io.Reader version of this constructor.
   *
   * @param   in  the java.io.Inputstream to read input from.
   */
  public SolarTokenMaker(java.io.InputStream in) {
    this(new java.io.InputStreamReader(in));
  }

  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    char [] map = new char[0x10000];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < 160) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }


  /**
   * Closes the input stream.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true;            /* indicate end of file */
    zzEndRead = zzStartRead;  /* invalidate buffer    */

    if (zzReader != null)
      zzReader.close();
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final String yytext() {
    return new String( zzBuffer, zzStartRead, zzMarkedPos-zzStartRead );
  }


  /**
   * Returns the character at position <tt>pos</tt> from the 
   * matched text. 
   * 
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch. 
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer[zzStartRead+pos];
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of 
   * yypushback(int) and a match-all fallback rule) this method 
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  } 


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public org.fife.ui.rsyntaxtextarea.Token yylex() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char [] zzBufferL = zzBuffer;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;
  
      zzState = zzLexicalState;


      zzForAction: {
        while (true) {
    
          if (zzCurrentPosL < zzEndReadL)
            zzInput = zzBufferL[zzCurrentPosL++];
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = zzBufferL[zzCurrentPosL++];
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          int zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
        case 5: 
          { addNullToken(); return firstToken;
          }
        case 25: break;
        case 16: 
          { start = zzMarkedPos-2; yybegin(MLC);
          }
        case 26: break;
        case 7: 
          { addToken(Token.WHITESPACE);
          }
        case 27: break;
        case 4: 
          { start = zzMarkedPos-1; yybegin(CHAR);
          }
        case 28: break;
        case 17: 
          { addToken(Token.LITERAL_NUMBER_FLOAT);
          }
        case 29: break;
        case 18: 
          { addToken(Token.RESERVED_WORD);
          }
        case 30: break;
        case 9: 
          { addToken(Token.SEPARATOR);
          }
        case 31: break;
        case 11: 
          { yybegin(YYINITIAL); addToken(start,zzStartRead, Token.LITERAL_CHAR);
          }
        case 32: break;
        case 2: 
          { addToken(Token.IDENTIFIER);
          }
        case 33: break;
        case 13: 
          { addToken(start,zzStartRead-1, Token.COMMENT_EOL); addNullToken(); return firstToken;
          }
        case 34: break;
        case 15: 
          { start = zzMarkedPos-2; yybegin(EOL_COMMENT);
          }
        case 35: break;
        case 21: 
          { addToken(Token.FUNCTION);
          }
        case 36: break;
        case 20: 
          { addToken(Token.DATA_TYPE);
          }
        case 37: break;
        case 19: 
          { yybegin(YYINITIAL); addToken(start,zzStartRead+2-1, Token.COMMENT_MULTILINE);
          }
        case 38: break;
        case 22: 
          { addToken(Token.LITERAL_BOOLEAN);
          }
        case 39: break;
        case 24: 
          { int temp=zzStartRead; addToken(start,zzStartRead-1, Token.COMMENT_EOL); addHyperlinkToken(temp,zzMarkedPos-1, Token.COMMENT_EOL); start = zzMarkedPos;
          }
        case 40: break;
        case 23: 
          { int temp=zzStartRead; addToken(start,zzStartRead-1, Token.COMMENT_MULTILINE); addHyperlinkToken(temp,zzMarkedPos-1, Token.COMMENT_MULTILINE); start = zzMarkedPos;
          }
        case 41: break;
        case 14: 
          { addToken(Token.ERROR_NUMBER_FORMAT);
          }
        case 42: break;
        case 6: 
          { start = zzMarkedPos-1; yybegin(STRING);
          }
        case 43: break;
        case 3: 
          { addToken(Token.LITERAL_NUMBER_DECIMAL_INT);
          }
        case 44: break;
        case 8: 
          { addToken(Token.OPERATOR);
          }
        case 45: break;
        case 10: 
          { yybegin(YYINITIAL); addToken(start,zzStartRead, Token.LITERAL_STRING_DOUBLE_QUOTE);
          }
        case 46: break;
        case 1: 
          { 
          }
        case 47: break;
        case 12: 
          { addToken(start,zzStartRead-1, Token.COMMENT_MULTILINE); return firstToken;
          }
        case 48: break;
        default: 
          if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
            zzAtEOF = true;
            switch (zzLexicalState) {
            case EOL_COMMENT: {
              addToken(start,zzStartRead-1, Token.COMMENT_EOL); addNullToken(); return firstToken;
            }
            case 226: break;
            case STRING: {
              addToken(start,zzStartRead-1, Token.LITERAL_STRING_DOUBLE_QUOTE); return firstToken;
            }
            case 227: break;
            case YYINITIAL: {
              addNullToken(); return firstToken;
            }
            case 228: break;
            case MLC: {
              addToken(start,zzStartRead-1, Token.COMMENT_MULTILINE); return firstToken;
            }
            case 229: break;
            case CHAR: {
              addToken(start,zzStartRead-1, Token.LITERAL_CHAR); return firstToken;
            }
            case 230: break;
            default:
            return null;
            }
          } 
          else {
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }


}

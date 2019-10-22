/* The following code was generated by JFlex 1.4.1 on 22/10/19 12:45 AM */

/*
 * Generated on 10/22/19 12:45 AM
 */
package eam.gui_compilador.Models;

import java.io.*;
import javax.swing.text.Segment;

import org.fife.ui.rsyntaxtextarea.*;


/**
 * 
 */

public class SolarTokenMaker extends AbstractJFlexTokenMaker {

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
    "\11\0\1\16\1\6\1\0\1\16\1\14\22\0\1\16\1\34\1\13"+
    "\1\15\1\1\1\40\1\36\1\5\2\35\1\20\1\37\1\33\1\23"+
    "\1\21\1\17\1\3\3\3\4\3\2\3\1\44\1\33\1\60\1\57"+
    "\1\46\1\34\1\15\1\53\5\4\7\1\1\47\14\1\1\35\1\7"+
    "\1\35\1\61\1\2\1\0\1\27\1\12\1\51\1\4\1\22\1\26"+
    "\1\56\1\41\1\43\1\1\1\50\1\30\1\55\1\11\1\52\1\42"+
    "\1\1\1\25\1\31\1\24\1\10\1\1\1\45\1\1\1\54\1\1"+
    "\1\32\1\62\1\32\1\33\uff81\0";

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
    "\1\6\1\7\2\10\2\2\1\10\5\2\1\11\1\10"+
    "\1\2\1\10\6\2\1\1\1\12\1\1\1\13\1\1"+
    "\1\14\5\1\1\15\3\1\1\0\1\16\1\0\1\16"+
    "\4\2\1\17\1\20\1\21\1\2\1\22\7\2\1\22"+
    "\4\2\1\23\11\0\1\21\1\0\12\2\1\24\4\2"+
    "\11\0\3\2\1\25\5\2\2\0\1\26\2\0\1\27"+
    "\1\0\6\2\5\0\3\2";

  private static int [] zzUnpackAction() {
    int [] result = new int[141];
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
    "\0\0\0\63\0\146\0\231\0\314\0\377\0\u0132\0\u0165"+
    "\0\377\0\377\0\u0198\0\u01cb\0\u01fe\0\377\0\u0231\0\u0264"+
    "\0\u0297\0\u02ca\0\u02fd\0\u0330\0\u0363\0\u0396\0\u03c9\0\u03fc"+
    "\0\u042f\0\377\0\377\0\u0462\0\u0495\0\u04c8\0\u04fb\0\u052e"+
    "\0\u0561\0\u0594\0\u05c7\0\u05fa\0\377\0\u062d\0\377\0\u0660"+
    "\0\377\0\u0693\0\u06c6\0\u06f9\0\u072c\0\u075f\0\377\0\u0792"+
    "\0\u07c5\0\u07f8\0\u082b\0\u085e\0\u02ca\0\u0891\0\u08c4\0\u08f7"+
    "\0\u092a\0\u095d\0\377\0\377\0\u0990\0\u09c3\0\377\0\u09f6"+
    "\0\u0a29\0\u0a5c\0\u0a8f\0\u0ac2\0\u0af5\0\u0b28\0\u0132\0\u0b5b"+
    "\0\u0b8e\0\u0bc1\0\u0bf4\0\377\0\u0c27\0\u0c5a\0\u0c8d\0\u0cc0"+
    "\0\u0cf3\0\u0d26\0\u0d59\0\u0d8c\0\u0dbf\0\u0df2\0\u0e25\0\u0e58"+
    "\0\u0e8b\0\u0ebe\0\u0ef1\0\u0f24\0\u0f57\0\u0f8a\0\u0fbd\0\u0ff0"+
    "\0\u1023\0\u0132\0\u1056\0\u1089\0\u10bc\0\u10ef\0\u1122\0\u1155"+
    "\0\u1188\0\u11bb\0\u11ee\0\u1221\0\u1254\0\u1287\0\u12ba\0\u12ed"+
    "\0\u1320\0\u1353\0\u0132\0\u1386\0\u13b9\0\u13ec\0\u141f\0\u1452"+
    "\0\u1485\0\u14b8\0\u14eb\0\u151e\0\u1551\0\u1584\0\u15b7\0\u15ea"+
    "\0\u161d\0\u1650\0\u1683\0\u16b6\0\u16e9\0\u171c\0\u14eb\0\u174f"+
    "\0\u1584\0\u1782\0\u17b5\0\u17e8\0\u181b";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[141];
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
    "\1\32\1\6\1\33\1\32\1\34\1\35\1\21\2\7"+
    "\1\36\1\33\1\37\1\21\1\40\1\7\1\41\1\7"+
    "\1\42\3\7\2\21\1\33\1\43\13\44\1\45\47\44"+
    "\5\46\1\47\55\46\6\50\1\51\11\50\1\52\5\50"+
    "\1\53\12\50\1\54\3\50\1\55\15\50\6\56\1\57"+
    "\17\56\1\60\12\56\1\61\3\56\1\62\15\56\64\0"+
    "\4\7\2\0\1\63\3\7\7\0\1\7\1\0\6\7"+
    "\7\0\3\7\1\0\1\7\1\0\10\7\4\0\3\64"+
    "\1\10\1\64\2\0\4\64\2\0\1\64\3\0\1\65"+
    "\1\66\1\0\6\64\7\0\3\64\1\0\1\64\1\0"+
    "\10\64\5\0\4\7\2\0\1\63\1\7\1\67\1\7"+
    "\7\0\1\7\1\0\6\7\7\0\3\7\1\0\1\7"+
    "\1\0\10\7\5\0\4\7\2\0\1\63\1\70\2\7"+
    "\7\0\1\7\1\0\6\7\7\0\3\7\1\0\1\7"+
    "\1\0\10\7\5\0\4\7\2\0\1\63\3\7\7\0"+
    "\1\7\1\0\1\7\1\71\4\7\7\0\3\7\1\0"+
    "\1\7\1\0\3\7\1\72\4\7\22\0\1\17\63\0"+
    "\1\73\1\74\36\0\1\33\62\0\1\33\6\0\1\75"+
    "\60\0\4\7\2\0\1\63\3\7\7\0\1\7\1\0"+
    "\4\7\1\76\1\7\7\0\3\7\1\0\1\7\1\0"+
    "\10\7\27\0\1\33\22\0\1\77\10\0\1\33\4\0"+
    "\4\7\2\0\1\63\3\7\7\0\1\7\1\0\1\7"+
    "\1\100\4\7\7\0\3\7\1\0\1\7\1\0\10\7"+
    "\5\0\4\7\2\0\1\63\3\7\7\0\1\101\1\0"+
    "\6\7\7\0\3\7\1\0\1\7\1\0\10\7\5\0"+
    "\4\7\2\0\1\63\1\102\2\7\7\0\1\7\1\0"+
    "\3\7\1\103\2\7\7\0\3\7\1\0\1\7\1\0"+
    "\3\7\1\104\4\7\5\0\4\7\2\0\1\63\1\7"+
    "\1\105\1\7\7\0\1\7\1\0\6\7\7\0\3\7"+
    "\1\0\1\7\1\0\10\7\5\0\4\7\2\0\1\63"+
    "\3\7\7\0\1\7\1\0\1\106\5\7\7\0\3\7"+
    "\1\0\1\7\1\0\10\7\42\0\1\33\63\0\1\33"+
    "\17\0\1\33\4\0\4\7\2\0\1\63\3\7\7\0"+
    "\1\7\1\0\2\7\1\107\3\7\7\0\3\7\1\0"+
    "\1\7\1\0\10\7\5\0\4\7\2\0\1\63\3\7"+
    "\7\0\1\7\1\0\6\7\7\0\1\110\2\7\1\0"+
    "\1\7\1\0\10\7\5\0\4\7\2\0\1\63\3\7"+
    "\7\0\1\7\1\0\3\7\1\111\2\7\7\0\3\7"+
    "\1\0\1\7\1\0\10\7\5\0\4\7\2\0\1\63"+
    "\3\7\7\0\1\7\1\0\6\7\7\0\3\7\1\0"+
    "\1\7\1\0\3\7\1\112\4\7\5\0\4\7\2\0"+
    "\1\63\3\7\7\0\1\7\1\0\1\7\1\113\4\7"+
    "\7\0\3\7\1\0\1\7\1\0\10\7\66\0\1\33"+
    "\13\44\1\0\47\44\5\46\1\0\55\46\6\50\1\0"+
    "\11\50\1\0\5\50\1\0\12\50\1\0\3\50\1\0"+
    "\15\50\17\0\1\114\67\0\1\115\16\0\1\116\43\0"+
    "\1\117\103\0\1\120\15\0\6\56\1\0\17\56\1\0"+
    "\12\56\1\0\3\56\1\0\15\56\24\0\1\121\16\0"+
    "\1\122\43\0\1\123\103\0\1\124\25\0\1\125\52\0"+
    "\5\64\2\0\4\64\2\0\1\64\4\0\1\64\1\0"+
    "\6\64\7\0\3\64\1\0\1\64\1\0\10\64\4\0"+
    "\3\64\1\126\1\64\2\0\4\64\2\0\1\64\4\0"+
    "\1\64\1\127\6\64\5\0\1\127\1\0\3\64\1\0"+
    "\1\64\1\0\10\64\5\0\4\7\2\0\1\63\3\7"+
    "\7\0\1\7\1\0\1\130\5\7\7\0\3\7\1\0"+
    "\1\7\1\0\10\7\5\0\4\7\2\0\1\63\3\7"+
    "\7\0\1\7\1\0\4\7\1\131\1\7\7\0\3\7"+
    "\1\0\1\7\1\0\6\7\1\132\1\7\5\0\4\7"+
    "\2\0\1\63\3\7\7\0\1\133\1\0\6\7\7\0"+
    "\3\7\1\0\1\7\1\0\10\7\5\0\4\7\2\0"+
    "\1\63\3\7\7\0\1\7\1\0\6\7\7\0\3\7"+
    "\1\0\1\7\1\0\3\7\1\134\4\7\4\0\3\64"+
    "\1\75\1\64\2\0\4\64\2\0\1\64\4\0\1\66"+
    "\1\0\6\64\7\0\3\64\1\0\1\64\1\0\10\64"+
    "\5\0\4\7\2\0\1\63\3\7\7\0\1\7\1\0"+
    "\5\7\1\135\7\0\3\7\1\0\1\7\1\0\10\7"+
    "\5\0\4\7\2\0\1\63\1\136\2\7\7\0\1\7"+
    "\1\0\6\7\7\0\3\7\1\0\1\7\1\0\10\7"+
    "\5\0\4\7\2\0\1\63\3\7\7\0\1\7\1\0"+
    "\1\137\5\7\7\0\3\7\1\0\1\7\1\0\10\7"+
    "\5\0\4\7\2\0\1\63\1\7\1\140\1\7\7\0"+
    "\1\7\1\0\6\7\7\0\3\7\1\0\1\7\1\0"+
    "\10\7\5\0\4\7\2\0\1\63\3\7\7\0\1\7"+
    "\1\0\4\7\1\141\1\7\7\0\3\7\1\0\1\7"+
    "\1\0\10\7\5\0\4\7\2\0\1\63\3\7\7\0"+
    "\1\7\1\0\1\7\1\107\4\7\7\0\3\7\1\0"+
    "\1\7\1\0\10\7\5\0\4\7\2\0\1\63\3\7"+
    "\7\0\1\7\1\0\6\7\7\0\3\7\1\0\1\7"+
    "\1\0\5\7\1\142\2\7\5\0\4\7\2\0\1\63"+
    "\3\7\7\0\1\7\1\0\1\7\1\143\4\7\7\0"+
    "\3\7\1\0\1\7\1\0\10\7\5\0\4\7\2\0"+
    "\1\63\3\7\7\0\1\7\1\0\6\7\7\0\2\7"+
    "\1\144\1\0\1\7\1\0\10\7\5\0\4\7\2\0"+
    "\1\63\3\7\7\0\1\7\1\0\6\7\7\0\3\7"+
    "\1\0\1\7\1\0\1\107\7\7\5\0\4\7\2\0"+
    "\1\63\1\7\1\145\1\7\7\0\1\7\1\0\6\7"+
    "\7\0\3\7\1\0\1\7\1\0\10\7\5\0\4\7"+
    "\2\0\1\63\3\7\7\0\1\7\1\0\1\7\1\146"+
    "\4\7\7\0\3\7\1\0\1\7\1\0\10\7\46\0"+
    "\1\147\50\0\1\150\56\0\1\151\103\0\1\152\57\0"+
    "\1\153\50\0\1\154\56\0\1\155\103\0\1\156\20\0"+
    "\2\157\5\0\1\157\7\0\1\157\3\0\2\157\21\0"+
    "\1\157\1\0\1\157\7\0\3\64\1\126\1\64\2\0"+
    "\4\64\2\0\1\64\4\0\1\64\1\0\6\64\7\0"+
    "\3\64\1\0\1\64\1\0\10\64\7\0\1\126\60\0"+
    "\4\7\2\0\1\63\3\7\7\0\1\7\1\0\6\7"+
    "\7\0\2\7\1\131\1\0\1\7\1\0\10\7\5\0"+
    "\4\7\2\0\1\63\3\7\7\0\1\7\1\0\4\7"+
    "\1\107\1\7\7\0\3\7\1\0\1\7\1\0\10\7"+
    "\5\0\4\7\2\0\1\63\2\7\1\160\7\0\1\7"+
    "\1\0\6\7\7\0\3\7\1\0\1\7\1\0\10\7"+
    "\5\0\4\7\2\0\1\63\3\7\7\0\1\7\1\0"+
    "\3\7\1\161\2\7\7\0\3\7\1\0\1\7\1\0"+
    "\10\7\5\0\4\7\2\0\1\63\3\7\7\0\1\7"+
    "\1\0\4\7\1\162\1\7\7\0\3\7\1\0\1\7"+
    "\1\0\10\7\5\0\4\7\2\0\1\63\3\7\7\0"+
    "\1\107\1\0\6\7\7\0\3\7\1\0\1\7\1\0"+
    "\10\7\5\0\4\7\2\0\1\63\3\7\7\0\1\163"+
    "\1\0\6\7\7\0\3\7\1\0\1\7\1\0\10\7"+
    "\5\0\4\7\2\0\1\63\1\164\2\7\7\0\1\7"+
    "\1\0\6\7\7\0\3\7\1\0\1\7\1\0\10\7"+
    "\5\0\4\7\2\0\1\63\3\7\7\0\1\7\1\0"+
    "\6\7\7\0\3\7\1\0\1\7\1\0\2\7\1\165"+
    "\5\7\5\0\4\7\2\0\1\63\3\7\7\0\1\7"+
    "\1\0\5\7\1\136\7\0\3\7\1\0\1\7\1\0"+
    "\10\7\5\0\4\7\2\0\1\63\3\7\7\0\1\7"+
    "\1\0\6\7\7\0\2\7\1\166\1\0\1\7\1\0"+
    "\10\7\5\0\4\7\2\0\1\63\3\7\7\0\1\7"+
    "\1\0\4\7\1\167\1\7\7\0\3\7\1\0\1\7"+
    "\1\0\10\7\5\0\4\7\2\0\1\63\3\7\7\0"+
    "\1\7\1\0\1\170\5\7\7\0\3\7\1\0\1\7"+
    "\1\0\10\7\5\0\4\7\2\0\1\63\3\7\7\0"+
    "\1\7\1\0\3\7\1\105\2\7\7\0\3\7\1\0"+
    "\1\7\1\0\10\7\50\0\1\171\40\0\1\147\102\0"+
    "\1\172\41\0\1\173\105\0\1\174\40\0\1\153\102\0"+
    "\1\175\41\0\1\176\44\0\2\177\5\0\1\177\7\0"+
    "\1\177\3\0\2\177\21\0\1\177\1\0\1\177\10\0"+
    "\4\7\2\0\1\63\3\7\7\0\1\200\1\0\6\7"+
    "\7\0\3\7\1\0\1\7\1\0\10\7\5\0\4\7"+
    "\2\0\1\63\3\7\7\0\1\7\1\0\6\7\7\0"+
    "\3\7\1\0\1\7\1\0\1\7\1\107\6\7\5\0"+
    "\4\7\2\0\1\63\3\7\7\0\1\201\1\0\6\7"+
    "\7\0\3\7\1\0\1\7\1\0\10\7\5\0\4\7"+
    "\2\0\1\63\3\7\7\0\1\7\1\0\1\7\1\202"+
    "\4\7\7\0\3\7\1\0\1\7\1\0\10\7\5\0"+
    "\4\7\2\0\1\63\3\7\7\0\1\7\1\0\1\203"+
    "\5\7\7\0\3\7\1\0\1\7\1\0\10\7\5\0"+
    "\4\7\2\0\1\63\1\7\1\204\1\7\7\0\1\7"+
    "\1\0\6\7\7\0\3\7\1\0\1\7\1\0\10\7"+
    "\5\0\4\7\2\0\1\63\3\7\7\0\1\7\1\0"+
    "\4\7\1\135\1\7\7\0\3\7\1\0\1\7\1\0"+
    "\10\7\5\0\4\7\2\0\1\63\3\7\7\0\1\7"+
    "\1\0\6\7\7\0\2\7\1\205\1\0\1\7\1\0"+
    "\10\7\23\0\1\206\74\0\1\147\12\0\1\171\17\0"+
    "\1\173\1\207\2\173\1\207\2\0\3\173\2\0\1\207"+
    "\1\0\1\173\2\207\1\173\1\207\6\173\1\0\6\207"+
    "\3\173\1\207\1\173\1\0\10\173\1\207\22\0\1\210"+
    "\74\0\1\153\12\0\1\174\17\0\1\176\1\211\2\176"+
    "\1\211\2\0\3\176\2\0\1\211\1\0\1\176\2\211"+
    "\1\176\1\211\6\176\1\0\6\211\3\176\1\211\1\176"+
    "\1\0\10\176\1\211\6\0\2\212\5\0\1\212\7\0"+
    "\1\212\3\0\2\212\21\0\1\212\1\0\1\212\10\0"+
    "\4\7\2\0\1\63\3\7\7\0\1\7\1\0\1\7"+
    "\1\142\4\7\7\0\3\7\1\0\1\7\1\0\10\7"+
    "\5\0\4\7\2\0\1\63\3\7\7\0\1\7\1\0"+
    "\3\7\1\213\2\7\7\0\3\7\1\0\1\7\1\0"+
    "\10\7\5\0\4\7\2\0\1\63\1\7\1\107\1\7"+
    "\7\0\1\7\1\0\6\7\7\0\3\7\1\0\1\7"+
    "\1\0\10\7\5\0\4\7\2\0\1\63\3\7\7\0"+
    "\1\7\1\0\6\7\7\0\2\7\1\214\1\0\1\7"+
    "\1\0\10\7\5\0\4\7\2\0\1\63\3\7\7\0"+
    "\1\7\1\0\6\7\7\0\3\7\1\0\1\7\1\0"+
    "\7\7\1\142\5\0\4\7\2\0\1\63\1\7\1\215"+
    "\1\7\7\0\1\7\1\0\6\7\7\0\3\7\1\0"+
    "\1\7\1\0\10\7\23\0\1\173\62\0\1\176\46\0"+
    "\2\7\5\0\1\7\7\0\1\7\3\0\2\7\21\0"+
    "\1\7\1\0\1\7\10\0\4\7\2\0\1\63\1\7"+
    "\1\142\1\7\7\0\1\7\1\0\6\7\7\0\3\7"+
    "\1\0\1\7\1\0\10\7\5\0\4\7\2\0\1\63"+
    "\3\7\7\0\1\7\1\0\6\7\7\0\3\7\1\0"+
    "\1\7\1\0\3\7\1\202\4\7\5\0\4\7\2\0"+
    "\1\63\1\135\2\7\7\0\1\7\1\0\6\7\7\0"+
    "\3\7\1\0\1\7\1\0\10\7\4\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[6222];
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
    "\13\1\2\11\11\1\1\11\1\1\1\11\1\1\1\11"+
    "\5\1\1\11\3\1\1\0\1\1\1\0\5\1\2\11"+
    "\2\1\1\11\14\1\1\11\11\0\1\1\1\0\17\1"+
    "\11\0\11\1\2\0\1\1\2\0\1\1\1\0\6\1"+
    "\5\0\3\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[141];
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
    while (i < 150) {
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
        case 24: break;
        case 16: 
          { start = zzMarkedPos-2; yybegin(MLC);
          }
        case 25: break;
        case 7: 
          { addToken(Token.WHITESPACE);
          }
        case 26: break;
        case 4: 
          { start = zzMarkedPos-1; yybegin(CHAR);
          }
        case 27: break;
        case 17: 
          { addToken(Token.LITERAL_NUMBER_FLOAT);
          }
        case 28: break;
        case 18: 
          { addToken(Token.RESERVED_WORD);
          }
        case 29: break;
        case 9: 
          { addToken(Token.SEPARATOR);
          }
        case 30: break;
        case 11: 
          { yybegin(YYINITIAL); addToken(start,zzStartRead, Token.LITERAL_CHAR);
          }
        case 31: break;
        case 2: 
          { addToken(Token.IDENTIFIER);
          }
        case 32: break;
        case 13: 
          { addToken(start,zzStartRead-1, Token.COMMENT_EOL); addNullToken(); return firstToken;
          }
        case 33: break;
        case 15: 
          { start = zzMarkedPos-2; yybegin(EOL_COMMENT);
          }
        case 34: break;
        case 20: 
          { addToken(Token.DATA_TYPE);
          }
        case 35: break;
        case 19: 
          { yybegin(YYINITIAL); addToken(start,zzStartRead+2-1, Token.COMMENT_MULTILINE);
          }
        case 36: break;
        case 21: 
          { addToken(Token.LITERAL_BOOLEAN);
          }
        case 37: break;
        case 23: 
          { int temp=zzStartRead; addToken(start,zzStartRead-1, Token.COMMENT_EOL); addHyperlinkToken(temp,zzMarkedPos-1, Token.COMMENT_EOL); start = zzMarkedPos;
          }
        case 38: break;
        case 22: 
          { int temp=zzStartRead; addToken(start,zzStartRead-1, Token.COMMENT_MULTILINE); addHyperlinkToken(temp,zzMarkedPos-1, Token.COMMENT_MULTILINE); start = zzMarkedPos;
          }
        case 39: break;
        case 14: 
          { addToken(Token.ERROR_NUMBER_FORMAT);
          }
        case 40: break;
        case 6: 
          { start = zzMarkedPos-1; yybegin(STRING);
          }
        case 41: break;
        case 3: 
          { addToken(Token.LITERAL_NUMBER_DECIMAL_INT);
          }
        case 42: break;
        case 8: 
          { addToken(Token.OPERATOR);
          }
        case 43: break;
        case 10: 
          { yybegin(YYINITIAL); addToken(start,zzStartRead, Token.LITERAL_STRING_DOUBLE_QUOTE);
          }
        case 44: break;
        case 1: 
          { 
          }
        case 45: break;
        case 12: 
          { addToken(start,zzStartRead-1, Token.COMMENT_MULTILINE); return firstToken;
          }
        case 46: break;
        default: 
          if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
            zzAtEOF = true;
            switch (zzLexicalState) {
            case EOL_COMMENT: {
              addToken(start,zzStartRead-1, Token.COMMENT_EOL); addNullToken(); return firstToken;
            }
            case 142: break;
            case STRING: {
              addToken(start,zzStartRead-1, Token.LITERAL_STRING_DOUBLE_QUOTE); return firstToken;
            }
            case 143: break;
            case YYINITIAL: {
              addNullToken(); return firstToken;
            }
            case 144: break;
            case MLC: {
              addToken(start,zzStartRead-1, Token.COMMENT_MULTILINE); return firstToken;
            }
            case 145: break;
            case CHAR: {
              addToken(start,zzStartRead-1, Token.LITERAL_CHAR); return firstToken;
            }
            case 146: break;
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
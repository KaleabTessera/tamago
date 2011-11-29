/**
 * 
 */
package org.tamago.eclipse.cdl.editor;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.DocumentCommand;
import org.eclipse.jface.text.IAutoEditStrategy;
import org.eclipse.jface.text.IDocument;

/**
 * @author Hakim Belhaouari
 *
 */
public class CDLAutoIndentStrategy implements IAutoEditStrategy {

	/**
	 * 
	 */
	public CDLAutoIndentStrategy() {
		// TODO Auto-generated constructor stub
	}
	
	// evaluate the line with the opening bracket that matches the closing bracket on the given line
	protected int findMatchingOpenBracket(IDocument d, int line, int end, int closingBracketIncrease) throws BadLocationException {

		int start= d.getLineOffset(line);
		int brackcount= getBracketCount(d, start, end, false) - closingBracketIncrease;

		// sum up the brackets counts of each line (closing brackets count negative, 
		// opening positive) until we find a line the brings the count to zero
		while (brackcount < 0) {
			line--;
			if (line < 0) {
				return -1;
			}
			start= d.getLineOffset(line);
			end= start + d.getLineLength(line) - 1;
			brackcount += getBracketCount(d, start, end, false);
		}
		return line;
	}

	private int getBracketCount(IDocument d, int start, int end, boolean ignoreCloseBrackets) throws BadLocationException {
		int bracketcount= 0;
		while (start < end) {
			char curr= d.getChar(start);
			start++;
			switch (curr) {
				case '/' :
					if (start < end) {
						char next= d.getChar(start);
						if (next == '*') {
							// a comment starts, advance to the comment end
							start= getCommentEnd(d, start + 1, end);
						} else if (next == '/') {
							// '//'-comment: nothing to do anymore on this line 
							start= end;
						}
					}
					break;
				case '{' :
					bracketcount++;
					ignoreCloseBrackets= false;
					break;
				case '}' :
					if (!ignoreCloseBrackets) {
						bracketcount--;
					}
					break;
				case '"' :
				case '\'' :
					start= getStringEnd(d, start, end, curr);
					break;
				default :
			}
		}
		return bracketcount;
	}

	// ----------- bracket counting ------------------------------------------------------

	private int getCommentEnd(IDocument d, int pos, int end) throws BadLocationException {
		while (pos < end) {
			char curr= d.getChar(pos);
			pos++;
			if (curr == '*') {
				if (pos < end && d.getChar(pos) == '/') {
					return pos + 1;
				}
			}
		}
		return end;
	}
	protected String getIndentOfLine(IDocument d, int line) throws BadLocationException {
		if (line > -1) {
			int start= d.getLineOffset(line);
			int end= start + d.getLineLength(line) - 1;
			int whiteend= findEndOfWhiteSpace(d, start, end);
			return d.get(start, whiteend - start);
		} else {
			return "";
		}
	}

	private int getStringEnd(IDocument d, int pos, int end, char ch) throws BadLocationException {
		while (pos < end) {
			char curr= d.getChar(pos);
			pos++;
			if (curr == '\\') {
				// ignore escaped characters
				pos++;
			} else if (curr == ch) {
				return pos;
			}
		}
		return end;
	}
	protected void smartInsertAfterBracket(IDocument d, DocumentCommand c) {
		if (c.offset == -1 || d.getLength() == 0)
			return;
		try {
			int p= (c.offset == d.getLength() ? c.offset - 1 : c.offset);
			int line= d.getLineOfOffset(p);
			int start= d.getLineOffset(line);
			int whiteend= findEndOfWhiteSpace(d, start, c.offset);

			// shift only when line does not contain any text up to the closing bracket
			if (whiteend == c.offset) {
				// evaluate the line with the opening bracket that matches out closing bracket
				int indLine= findMatchingOpenBracket(d, line, c.offset, 1);
				if (indLine != -1 && indLine != line) {
					// take the indent of the found line
					StringBuffer replaceText= new StringBuffer(getIndentOfLine(d, indLine));
					// add the rest of the current line including the just added close bracket
					replaceText.append(d.get(whiteend, c.offset - whiteend));
					replaceText.append(c.text);
					// modify document command
					c.length= c.offset - start;
					c.offset= start;
					c.text= replaceText.toString();
				}
			}
		} catch (BadLocationException excp) {
		}
	}

	
	protected void smartIndentAfterNewLine(IDocument d, DocumentCommand c) {
		int docLength= d.getLength();
		if (c.offset == -1 || docLength == 0)
			return;
		try {
			int p= (c.offset == docLength ? c.offset - 1 : c.offset);
			int line= d.getLineOfOffset(p);

			StringBuffer buf= new StringBuffer(c.text);
			if (c.offset < docLength && d.getChar(c.offset) == '}') {
				int indLine= findMatchingOpenBracket(d, line, c.offset, 0);
				if (indLine == -1) {
					indLine= line;
				}
				buf.append(getIndentOfLine(d, indLine));
			} else {
				int start= d.getLineOffset(line);
				int whiteend= findEndOfWhiteSpace(d, start, c.offset);
				buf.append(d.get(start, whiteend - start));
				if (getBracketCount(d, start, c.offset, true) > 0) {
					buf.append('\t');
				}
			}
			c.text= buf.toString();

		} catch (BadLocationException excp) {
			
		}
	}
	
	/**
	 * Returns whether the text ends with one of the given search strings.
	 */
	private boolean endsWithDelimiter(IDocument d, String txt) {
		String[] delimiters= d.getLegalLineDelimiters();
		
		for (int i= 0; i < delimiters.length; i++) {
			if (txt.endsWith(delimiters[i]))
				return true;
		}
		return false;
	}	


	public void customizeDocumentCommand(IDocument d, DocumentCommand c) {
		if (c.length == 0 && c.text != null && endsWithDelimiter(d, c.text))
			smartIndentAfterNewLine(d, c);
		else if ("}".equals(c.text)) {
			smartInsertAfterBracket(d, c);
		}
	}

	
	
	
	protected int findEndOfWhiteSpace(IDocument document, int offset, int end) throws BadLocationException {
		while (offset < end) {
			char c= document.getChar(offset);
			if (c != ' ' && c != '\t') {
				return offset;
			}
			offset++;
		}
		return end;
	}
	
	
	
	
	
	
	// ---------------------------------------------------------------------------
	

//	
//	
//
//	/**
//	 * Copies the indentation of the previous line.
//	 *
//	 * @param d the document to work on
//	 * @param c the command to deal with
//	 */
//	private void autoIndentAfterNewLine(IDocument d, DocumentCommand c) {
//
//		if (c.offset == -1 || d.getLength() == 0)
//			return;
//
//		try {
//			// find start of line
//			int p= (c.offset == d.getLength() ? c.offset  - 1 : c.offset);
//			IRegion info= d.getLineInformationOfOffset(p);
//			int start= info.getOffset();
//
//			// find white spaces
//			int end= findEndOfWhiteSpace(d, start, c.offset);
//
//			StringBuffer buf= new StringBuffer(c.text);
//			if (end > start) {
//				// append to input
//				buf.append(d.get(start, end - start));
//			}
//
//			c.text= buf.toString();
//
//		} catch (BadLocationException excp) {
//			// stop work
//		}
//	}
//
//	/*
//	 * @see org.eclipse.jface.text.IAutoEditStrategy#customizeDocumentCommand(org.eclipse.jface.text.IDocument, org.eclipse.jface.text.DocumentCommand)
//	 */
//	public void customizeDocumentCommand(IDocument d, DocumentCommand c) {
//		if (c.length == 0 && c.text != null && TextUtilities.endsWith(d.getLegalLineDelimiters(), c.text) != -1)
//			autoIndentAfterNewLine(d, c);
//	}

}

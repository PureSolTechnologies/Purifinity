package com.puresol.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * This class was inspired by:
 * http://www.heatonresearch.com/articles/22/page2.html
 * 
 * This class is a helper class for binary file processing.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class BinaryFile {

	private Encoding encoding = Encoding.LITTLE_ENDIAN;
	private final RandomAccessFile raFile;

	public BinaryFile(File file, String mode) throws FileNotFoundException {
		raFile = new RandomAccessFile(file, mode);
	}

	public BinaryFile(String name, String mode) throws FileNotFoundException {
		raFile = new RandomAccessFile(name, mode);
	}

	public void close() throws IOException {
		raFile.close();
	}

	public void setEndian(Encoding encoding) {
		this.encoding = encoding;
	}

	public Encoding getEndian() {
		return encoding;
	}

	/**
	 * This method reads a defined length portion out of the file and creates a
	 * String out of it.
	 * 
	 * @param length
	 * @return
	 * @throws IOException
	 */
	public String readFixedString(int length) throws IOException {
		byte buffer[] = new byte[length];
		int actualLength = raFile.read(buffer, 0, length);
		return new String(buffer, 0, actualLength);
	}

	public void writeFixedString(String str, int length) throws IOException {
		int i;
		if (str.length() > length) {
			str = str.substring(0, length);
		}
		raFile.write(str.getBytes(), 0, str.length());
		i = length - str.length();
		while ((i--) > 0) {
			raFile.write(0);
		}
	}

	public String readFixedZeroString(int length) throws IOException {
		String str = readFixedString(length);
		int i = str.indexOf(0);
		if (i >= 0) {
			str = str.substring(0, i);
		}
		return str;
	}

	public void writeFixedZeroString(String str, int length) throws IOException {
		writeFixedString(str, length);
	}

	public String readZeroString() throws IOException {
		StringBuffer buffer = new StringBuffer();
		char c;
		do {
			c = raFile.readChar();
			if (c != 0) {
				buffer.append(c);
			}
		} while (c != 0);
		return buffer.toString();
	}

	public void writeZeroString(String str) throws java.io.IOException {
		raFile.write(str.getBytes(), 0, str.length());
		writeByte((byte) 0);
	}

	public String readLengthPrefixString() throws IOException {
		int len = readUnsignedByte();
		return readFixedString(len);
	}

	public void writeLengthPrefixString(String str) throws IOException {
		writeByte((byte) str.length());
		raFile.write(str.getBytes(), 0, str.length());
	}

	public short readUnsignedByte() throws IOException {
		return (short) raFile.readUnsignedByte();
	}

	public byte readSignedByte() throws IOException {
		return (byte) raFile.readByte();
	}

	public void writeByte(short b) throws IOException {
		raFile.write(b & 0xff);
	}

	public int readUnsignedShort() throws IOException {
		int byte1 = readUnsignedByte();
		int byte2 = readUnsignedByte();
		int result;
		if (encoding == Encoding.BIG_ENDIAN) {
			result = (byte1 << 8) | byte2;
		} else {
			result = (byte2 << 8) | byte1;
		}
		return result;
	}

	public short readSignedShort() throws IOException {
		int byte1 = readUnsignedByte();
		int byte2 = readUnsignedByte();
		int result;
		if (encoding == Encoding.BIG_ENDIAN) {
			result = (byte1 << 8) | byte2;
		} else {
			result = (byte2 << 8) | byte1;
		}
		if ((result & 0x8000) == 0x8000)
			result = -(0x10000 - result);
		return (short) result;
	}

	public void writeShort(int s) throws IOException {
		if (encoding == Encoding.BIG_ENDIAN) {
			raFile.write((s & 0xff00) >> 8);
			raFile.write(s & 0xff);
		} else {
			raFile.write(s & 0xff);
			raFile.write((s & 0xff00) >> 8);
		}
	}

	public long readUnsignedInt() throws IOException {
		int byte1 = readUnsignedByte();
		int byte2 = readUnsignedByte();
		int byte3 = readUnsignedByte();
		int byte4 = readUnsignedByte();
		long result;
		if (encoding == Encoding.BIG_ENDIAN) {
			result = (byte1 << 24) | (byte2 << 16) | (byte3 << 8) | (byte4);
		} else {
			result = (byte4 << 24) | (byte3 << 16) | (byte2 << 8) | (byte1);
		}
		return result;
	}

	public int readSignedInt() throws IOException {
		int byte1 = readUnsignedByte();
		int byte2 = readUnsignedByte();
		int byte3 = readUnsignedByte();
		int byte4 = readUnsignedByte();
		long result;
		if (encoding == Encoding.BIG_ENDIAN) {
			result = (byte1 << 24) | (byte2 << 16) | (byte3 << 8) | (byte4);
		} else {
			result = (byte4 << 24) | (byte3 << 16) | (byte2 << 8) | (byte1);
		}
		if ((result & 0x80000000) == 0x80000000)
			result = -(0x100000000L - result);
		return (int) result;
	}

	public void writeInt(long d) throws IOException {
		if (encoding == Encoding.BIG_ENDIAN) {
			raFile.write((int) (d & 0xff000000) >> 24);
			raFile.write((int) (d & 0xff0000) >> 16);
			raFile.write((int) (d & 0xff00) >> 8);
			raFile.write((int) (d & 0xff));
		} else {
			raFile.write((int) (d & 0xff));
			raFile.write((int) (d & 0xff00) >> 8);
			raFile.write((int) (d & 0xff0000) >> 16);
			raFile.write((int) (d & 0xff000000) >> 24);
		}
	}

}

/***************************************************************************
 String.cpp  -  description
 -------------------
 begin                : Fri Jan 02 09:09:00 CET 2004
 copyright            : (C) 2004 by Rick-Rainer Ludwig
 email                : rl719236@users.sourceforge.net
 ***************************************************************************/

/***************************************************************************
 *                                                                         *
 *   This program is free software; you can redistribute it and/or modify  *
 *   it under the terms of the GNU General Public License as published by  *
 *   the Free Software Foundation; either Version 2 of the License, or     *
 *   (at your option) any later Version.                                   *
 *                                                                         *
 ***************************************************************************/

#include "String.h"

String::String() :
	Root(), string("")
{
}

String::String(const char *str) :
	Root(), string(str)
{
}

String::String(char c) :
	Root(), string()
{
	push_back(c);
}

String::String(const string &s) :
	Root(), string(s)
{
}

String::String(const String &s) :
	Root(), string(s)
{
}

String::String(double d) :
	Root(), string()
{
	char str[24];
	sprintf(str, "%g", d);
	*this = str;
}

String::String(int i) :
	Root(), string()
{
	char str[24];
	sprintf(str, "%i", i);
	*this = str;
}

String::String(unsigned int i) :
	Root(), string()
{
	char str[24];
	sprintf(str, "%i", i);
	*this = str;
}

String::~String()
{
}

String &String::setString(const String &str)
{
	*this = str;
	return *this;
}

void String::printQuoted(FILE *file) const
{
	String("'%1'").arg(ansi()).printLine(file);
}

void String::printLineQuoted(FILE *file) const
{
	String("'%1'").arg(ansi()).printLine(file);
}

int String::write(FILE *file) const
{
	if (file)
	{
		unsigned int len = length();
		fwrite(&len, sizeof(unsigned int), 1, file);
		fwrite(ansi(), sizeof(char), length(), file);
	}
	return length();
}

int String::writeNullTerminated(FILE *file) const
{
	if (file)
	{
		fwrite(ansi(), sizeof(char), length() + 1, file);
	}
	return length();
}

int String::read(FILE *file)
{
	*this = "";
	if (!file)
	{
		return 0;
	}
	if (feof(file))
	{
		return 0;
	}
	unsigned int len = 0;
	int retlen = fread(&len, 1, sizeof(unsigned int), file);
	if (feof(file))
	{
		return 0;
	}
	if (ferror(file))
	{
		return 0;
	}
	if (retlen != sizeof(unsigned int))
	{
		return 0;
	}
	if (len <= 0)
	{
		return 0;
	}
	read(file, len);
	return length();
}

int String::readNullTerminated(FILE *file)
{
	*this = "";
	if (file)
	{
		char s[2];
		s[0] = 0;
		s[1] = 0;
		fread(s, sizeof(char), 1, file);
		while ((s[0] != 0) && (!feof(file)))
		{
			append(s);
			fread(s, sizeof(char), 1, file);
		}
	}
	return length();
}

int String::read(FILE *file, int len)
{
	*this = "";
	if (!file)
	{
		return 0;

	}
	if (len <= 0)
	{
		return 0;
	}
	if (feof(file))
	{
		return 0;
	}
	struct stat st;
	if (fstat(fileno(file), &st) == -1)
	{
		return 0;
	}
	if (st.st_size <= ftell(file) + 4)
	{
		return 0;
	}
	char *s = new char[len + 1];
	int retlen = fread(s, sizeof(char), len, file);
	s[retlen] = 0;
	*this = s;
	delete[] s;
	return length();
}

int String::readLine(FILE *file)
{
	char s[DEFAULT_STRING_BUFFER_SIZE];
	s[0] = 0;
	*this = "";
	do
	{
		if (fgets(s, DEFAULT_STRING_BUFFER_SIZE, file) > 0)
		{
			string::append(s);
		}
	} while (((strlen(s) == DEFAULT_STRING_BUFFER_SIZE - 1)
			&& (!contains("\n"))) && (!feof(file)));
	return length();
}

int String::countSubString(const String &subStr) const
{
	int result = 0;
	String tmp(*this);
	while (tmp.isSubString(subStr))
	{
		result++;
		tmp = right(tmp.length() - tmp.find(subStr) - subStr.length());
	}
	return result;
}

bool String::toBool() const
{
	if (isEmpty())
	{
		return false;
	}
	String tmp(*this);
	tmp.toLower();
	if ((tmp == "false") || (tmp == "off") || (tmp == "stop") || (tmp
			== "disabled") || (tmp == "0") || (tmp == "n") || (tmp == "no"))
	{
		return false;
	}
	return true;
}

double String::toDouble(bool *ok) const
{
	double d;
	int ret;
	if (ok != NULL)
		*ok = true;
	if (length() > 0)
	{
		ret = sscanf(ansi(), "%lg", &d);
		if (ok != NULL)
		{
			if (ret == EOF)
				*ok = false;
			if (ret <= 0)
				*ok = false;
		}
	}
	else
	{
		d = 0.0;
		if (ok != NULL)
			ok = false;
	}
	return d;
}

float String::toFloat(bool *ok) const
{
	return (float) toDouble(ok);
}

int String::toInt(bool *ok) const
{
	if (ok != NULL)
	{
		*ok = true;
	}
	int i;
	if (length() > 0)
	{
		String temp(*this);
		temp.strip();
		while (temp.startsWith("0") && (temp.length() > 1))
		{
			temp.remove(0, 1);
		}
		int ret = sscanf(temp.ansi(), "%i", &i);
		if (ok != NULL)
		{
			if (ret == EOF)
			{
				*ok = false;
			}
			if (ret <= 0)
			{
				*ok = false;
			}
		}
	}
	else
	{
		i = 0;
	}
	return i;
}

short String::toShort(bool *ok) const
{
	return (short) toInt(ok);
}

String &String::toUpper()
{
	for (unsigned int i = 0; i < length(); i++)
		(*this)[i] = toupper((*this)[i]);
	return *this;
}

String String::upper() const
{
	String up(*this);
	return up.toUpper();
}

String &String::toLower()
{
	for (size_type i = 0; i < length(); i++)
	{
		(*this)[i] = tolower((*this)[i]);
	}
	return *this;
}

String String::lower() const
{
	String low(*this);
	return low.toLower();
}

void String::cut(const String &sub, String *before, String *middle,
		String *behind) const
{
	if (!sub)
	{
		return;
	}
	int subLen = sub.length();
	int subPos = find(sub);
	if (subPos < 0)
	{
		subPos = length();
		subLen = 0;
	}
	if (behind)
	{
		*behind = right(length() - subPos - subLen);
	}
	if (before)
	{
		*before = left(subPos);
	}
	if (middle)
	{
		*middle = sub;
	}
}

String String::cutTwice(const String &str1, const String &str2)
{
	if ((!contains(str1)) || (!contains(str2)))
	{
		return "";
	}
	String tmp(right(length() - find(str1) - 1));
	return tmp.left(tmp.find(str2));
}

String &String::replace(const String &oldSub, const String &newSub)
{
	if (!contains(oldSub))
		return *this;
	String tmp(left(find(oldSub)));
	tmp += newSub;
	tmp += right(length() - find(oldSub) - oldSub.length());
	*this = tmp;
	return *this;
}

String &String::replaceAll(const String &oldSub, const String &newSub)
{
	if (isEmpty())
	{
		return *this;
	}
	String oldStr(*this);
	*this = "";
	String before, behind;
	while (oldStr.isSubString(oldSub))
	{
		oldStr.cut(oldSub, &before, NULL, &behind);
		append(before);
		append(newSub);
		oldStr = behind;
	}
	return append(oldStr);
}

String &String::stripStart()
{
	unsigned int pos = 0;
	while ((!isgraph((*this)[pos])) && (pos < length()))
		pos++;
	return remove(0, pos);
}

String &String::stripEnd()
{
	unsigned int pos = 0;
	if (length() > 0)
		while ((!isgraph((*this)[length() - pos - 1])) && (pos < length()))
			pos++;
	return remove(length() - pos, pos);
}

String &String::strip()
{
	stripStart();
	return stripEnd();
}

String &String::toChar(char c)
{
	for (unsigned int i = 0; i < length(); i++)
		if (isgraph((*this)[i]))
			(*this)[i] = c;
	return *this;
}

String &String::toWhiteString()
{
	return toChar(' ');
}

String &String::removeCR()
{
	if (length() <= 0)
		return *this;

	while ((ansi()[length() - 1] == 13) || (ansi()[length() - 1] == 10))
	{
		remove(length() - 1, 1);
		if (length() == 0)
		{
			break;
		}
	}

	return *this;
}

bool String::endsWithCR()
{
	return (endsWithWinCR() || endsWithUnixCR());
}

bool String::endsWithWinCR()
{
	if ((ansi()[length()] == 10) && (ansi()[length() - 1] == 13))
		return true;
	return false;
}

bool String::endsWithUnixCR()
{
	if (ansi()[length()] == 13)
		return true;
	return false;
}

String &String::appendWinCR()
{
	append((char) 13);
	return append((char) 10);
}

String &String::appendUnixCR()
{
	return append((char) 10);
}

String &String::insert(const String &str, int pos)
{
	String tmp(left(pos) + str + right(length() - pos));
	*this = tmp;
	return *this;
}

String &String::arg(const String &str)
{
	char c[3];
	c[0] = '%';
	c[1] = '0';
	c[2] = 0;
	do
	{
		c[1]++;
		if (c[1] > '9')
		{
			return *this;
		}
	} while ((int) find(c) < 0);
	return replaceAll(c, str);
}

String &String::arg(double d, unsigned int width, char format, int prec)
{
	String str("%");
	char s[DEFAULT_STRING_BUFFER_SIZE];
	if (width > 0)
	{
		str.append(String("%1").arg(width));
	}
	if (prec >= 0)
	{
		str.append(String(".%1").arg(prec));
	}
	str.append(format);
	sprintf(s, str.ansi(), d);
	return arg(s);
}

String &String::arg(int i, unsigned int width, unsigned int base, bool sign)
{
	return arg((long int) i, width, base, sign);
}

String &String::arg(long int i, unsigned int width, unsigned int base,
		bool sign)
{
	String argStr;
	bool minus = false;
	int r = 0, d;
	if (i < 0)
	{
		minus = true;
		i *= -1;
	}
	if (i == 0)
	{
		argStr = "0";
	}
	else
	{
		while (i > 0)
		{
			d = i % base + r;
			i = i / base;
			if ((!sign) && (minus))
			{
				d = base - d;
				r = 1;
			}
			if (d < 10)
				argStr.insert(String((char) ('0' + d)), 0);
			else
				argStr.insert(String((char) (97 + d - 10)), 0);
		}
	}
	if (sign && minus)
		argStr.insert("-", 0);
	while (argStr.length() < width)
	{
		if (!sign)
			if (minus)
				if (base - 1 < 10)
					argStr.insert(String((char) (base - 1 + '0')), 0);
				else
					argStr.insert(String((char) (97 + base - 11)), 0);
			else
				argStr.insert("0", 0);
		else
			argStr.insert(" ", 0);
	}
	if (argStr.isEmpty())
		argStr = "0";
	return arg(argStr);
}

String &String::arg(unsigned int i, unsigned int width, unsigned int base,
		bool sign)
{
	return arg((int) i, width, base, sign);
}

String &String::arg(unsigned long int i, unsigned int width, unsigned int base,
		bool sign)
{
	return arg((long int) i, width, base, sign);
}

String &String::argBool(bool b, const String &yes, const String &no)
{
	if (b)
	{
		return arg(yes);
	}
	return arg(no);
}

int String::iccompare(const String &str1, const String &str2)
{
	return strcasecmp(str1.ansi(), str2.ansi());
}

int String::compare(const String &str1, const String &str2)
{
	return strcmp(str1.ansi(), str2.ansi());
}

int String::compareWithWC(String strWC, String str)
{
	if (str.isSubString("?") || (str.isSubString("*")))
		return false;
	strWC.replaceAll("?", ".");
	strWC.replaceAll("*", ".*");
	return compareRegExp(strWC, str);
}

int String::compareWithWC(const String &str) const
{
	return compareWithWC(*this, str);
}

bool String::compareRegExp(String regExpStr, String str)
{
	int errorOffset;
	int output[64], result;
	const char *error;
	pcre *regexp;

	if (regExpStr.isEmpty())
		return str.isEmpty();

	regexp = pcre_compile(regExpStr.ansi(), 0, &error, &errorOffset, NULL);
	if (!regexp)
		return false;

	result
			= pcre_exec(regexp, NULL, str.ansi(), str.length(), 0, 0, output,
					64);
	if (result < 0)
	{
		free(regexp);
		return false;
	}
	free(regexp);
	return true;
}

bool String::compareRegExp(const String &regExpStr) const
{
	return compareRegExp(regExpStr, *this);
}

pcre *String::compileRegExp(const String &regExp)
{
	int pcreErrorOffset;
	const char *pcreError;
	pcre *pcreRegExp;
	pcreRegExp = pcre_compile(regExp.ansi(), 0, &pcreError, &pcreErrorOffset,
			NULL);
	return pcreRegExp;
}

bool String::compareRegExp(pcre *pcreRegExp)
{
	int pcreOutput[64];
	if (!pcreRegExp)
	{
		return false;
	}
	int result = pcre_exec(pcreRegExp, NULL, ansi(), length(), 0, 0,
			pcreOutput, 64);
	if (result < 0)
	{
		return false;
	}
	return true;
}

String String::addTrailingChars(unsigned int len, char c) const
{
	String spaces;
	for (unsigned int i = length(); i < len; i++)
	{
		spaces += c;
	}
	return String(*this) + spaces;
}

String String::addLeadingChars(unsigned int len, char c) const
{
	String spaces;
	for (unsigned int i = length(); i < len; i++)
	{
		spaces += c;
	}
	return spaces + *this;
}

package io.springofficehours.cli.mdtohtml;

import org.springframework.shell.command.annotation.Command;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Command
public class Converter {

	@Command
	public void convert() {
		try {
			String input = new String(Files.readAllBytes(Paths.get("./README.md")));
			String content = convertMarkdownToBookmark(input);
			File file = new File("./bookmarks.html");
			FileWriter fileWriter = new FileWriter(file, false);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write(content);
			bufferedWriter.flush();
			bufferedWriter.close();
			fileWriter.close();
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private static String convertMarkdownToBookmark(String markdown) {
		var time = System.currentTimeMillis() / 1000L;
		StringBuilder bookmark = new StringBuilder();
		bookmark.append("<!DOCTYPE NETSCAPE-Bookmark-file-1>\n")
			.append("<!-- This is an automatically generated file.\n")
			.append("     It will be read and overwritten.\n")
			.append("     DO NOT EDIT! -->\n")
			.append("<META HTTP-EQUIV=\"Content-Type\" CONTENT=\"text/html; charset=UTF-8\">\n")
			.append("<TITLE>Bookmarks</TITLE>\n")
			.append("<H1>Bookmarks</H1>\n")
			.append("<DL><p>\n")
			.append(String.format(
					"    <DT><H3 ADD_DATE=\"%s\" LAST_MODIFIED=\"%s\" PERSONAL_TOOLBAR_FOLDER=\"true\">Bookmarks Bar</H3>\n",
					time, time))
			.append("    <DL><p>\n")
			.append(String.format("        <DT><H3 ADD_DATE=\"%s\" LAST_MODIFIED=\"%s\">Learning Spring</H3>\n", time,
					time))
			.append("        <DL><p>\n");

		// Regex patterns for different Markdown structures
		Pattern categoryPattern = Pattern.compile("##\\s+(.+)");
		Pattern linkPattern = Pattern.compile("-\\s+\\[(.+)\\]\\((.+)\\)");

		String[] lines = markdown.split("\n");
		String currentCategory = null;

		for (String line : lines) {
			Matcher categoryMatcher = categoryPattern.matcher(line);
			Matcher linkMatcher = linkPattern.matcher(line);

			if (categoryMatcher.find()) {
				if (currentCategory != null) {
					bookmark.append("            </DL><p>\n");
				}
				currentCategory = categoryMatcher.group(1);
				bookmark.append("            <DT><H3 ADD_DATE=\"")
					.append(time)
					.append("\" LAST_MODIFIED=\"")
					.append(time)
					.append("\">")
					.append(currentCategory)
					.append("</H3>\n")
					.append("            <DL><p>\n");
			}
			else if (linkMatcher.find()) {
				String linkText = linkMatcher.group(1);
				String linkHref = linkMatcher.group(2);
				bookmark.append("                <DT><A HREF=\"")
					.append(linkHref)
					.append("\" ADD_DATE=\"")
					.append(time)
					.append("\">")
					.append(linkText)
					.append("</A>\n");
			}
		}

		if (currentCategory != null) {
			bookmark.append("            </DL><p>\n");
		}

		bookmark.append("        </DL><p>\n").append("    </DL><p>\n").append("</DL><p>\n");

		return bookmark.toString();
	}

}

package view;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class IssueFileFilter extends FileFilter {

	@Override
	public boolean accept(File file) {
		String name = file.getName();
		String extension = Utils.getFileExtension(name);

		if (extension == null) {
			return false;
		}
		if (extension.equals("iss")) {
			return true;
		}
		if (extension.equals("txt")) {
			return true;
		}
		if (extension.equals("xlsx")) {
			return true;
		}
		if (extension.equals("csv")) {
			return true;
		}
		return false;
	}

	@Override
	public String getDescription() {
		return "Issue File (-.iss)";
	}
}

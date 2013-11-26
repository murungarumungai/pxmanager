package raz.test.file.io;

/*
 * Copyright (c) 1995 - 2008 Sun Microsystems, Inc.  All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Sun Microsystems nor the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static java.lang.System.out;

/**
 * The FileStuff example creates File objects from names passed from the command
 * line and exercises various information methods on them. You'll find it
 * instructive to run FileStuff on a variety of file names. Be sure to include
 * directory names as well as the names of files that don't actually exist. Try
 * passing FileStuff a variety of relative and absolute path names.
 * 
 * */

public class FileStuff {
	public static void main(String args[]) throws IOException {

		out.print("File system roots: ");
		for (File root : File.listRoots()) {
			out.format("%s ", root);
		}
		out.println();

		for (String fileName : args) {
			out.format("%n------%nnew File(%s)%n", fileName);
			File f = new File(fileName);
			out.format("toString(): %s%n", f);
			out.format("exists(): %b%n", f.exists());
			out.format("lastModified(): %tc%n", f.lastModified());
			out.format("isFile(): %b%n", f.isFile());
			out.format("isDirectory(): %b%n", f.isDirectory());
			out.format("isHidden(): %b%n", f.isHidden());
			out.format("canRead(): %b%n", f.canRead());
			out.format("canWrite(): %b%n", f.canWrite());
			out.format("canExecute(): %b%n", f.canExecute());
			out.format("isAbsolute(): %b%n", f.isAbsolute());
			out.format("length(): %d%n", f.length());
			out.format("getName(): %s%n", f.getName());
			out.format("getPath(): %s%n", f.getPath());
			out.format("getAbsolutePath(): %s%n", f.getAbsolutePath());
			out.format("getCanonicalPath(): %s%n", f.getCanonicalPath());
			out.format("getParent(): %s%n", f.getParent());
			out.format("toURI: %s%n", f.toURI());
		}
	}

	/**
	 * Gets all the children of this file If file is a File and not a dir we
	 * return null
	 * 
	 * @param parent
	 *            must be a valid File Path on the FileSystem
	 * */
	static List<File> getChildren(File parent) {
		List<File> children = null;

		if (parent.isDirectory()) {
			children = Arrays.asList(parent.listFiles());
			if (children == null || children.isEmpty()) {
				// stop recursion
			} else {
				for (File oneFile : children) {
					if (oneFile.exists()) {
						return getChildren(oneFile);
					}
				}
			}
		} else {
			// file is File, stop recursion
			// add this
		}

		return children;
	}
}

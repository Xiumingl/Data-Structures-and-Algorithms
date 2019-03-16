package RedBlackTreeSpellChecker;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class RedBlackTreeSpellChecker {

	public static void menu(String infile, RedBlackTree tree) {
		System.out.println("Loading a tree of English words from " + infile);
		System.out.println("Red Black Tree is loaded with  " + tree.getSize());
		System.out.println("Initial tree height is  " + tree.height());
		System.out.println("Never worse than 2*Lg(n+1) =" + 2 * Math.log(tree.getSize() + 1));
		System.out.println("Legal Commands are:");
		System.out.println("d	display the entire word tree with a level order traversal");
		System.out.println("s	print the words of the tree in sorted order (using an inorder traversal)");
		System.out.println("r	print the words of the tree in reverse sorted order (reverse inorder traversal)");
		System.out.println("c   <word> to spell check this word");
		System.out.println("a   <word> add this word to tree");
		System.out.println("f   <fileName> to check this text file for spelling errors");
		System.out.println("i   display the diameter of the tree");
		System.out.println("m  	view this menu");
		System.out.println("!   to quit");

	}

	public static void main(String[] args) throws FileNotFoundException {
		RedBlackTree tree = new RedBlackTree();
		String infile = args[0];
		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(infile), "UTF8"))) {
			String cLine;
			while ((cLine = br.readLine()) != null) {
				tree.insert(cLine);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		menu(args[0], tree);
		boolean running = true;

		while (running) {
			Scanner sc = new Scanner(System.in);
			String command = sc.next();
			switch (command) {
			case "d":
				tree.levelOrderTraversal();
				break;

			case "s":
				tree.inOrderTraversal();
				break;

			case "r":
				tree.reverseOrderTraversal();
				break;

			case "i":
				System.out.println("The diameter of the tree is " + tree.getDiameter());
				break;

			case "m":
				menu(args[0], tree);
				break;

			case "!":
				System.out.println("Bye");
				running = false;
				break;

			case "c":
				String word1 = sc.next();
				if (tree.contains(word1)) {
					System.out.println("Found " + word1 + " after " + tree.getRecentCompares() + " comparisons");
				} else {

					System.out.println(word1 + " Not in dictionary. Perhaps you mean " + tree.closeBy(word1));
				}
				break;

			case "a":
				String word2 = sc.next();
				if (tree.contains(word2)) {
					System.out.println(word2 + " already in dictionary ");
				} else {
					tree.insert(word2);
					System.out.println(word2 + " was added to dictionary ");
				}

				break;

			case "f":
				String fileName = sc.next();
				int error = 0;
				File file = new File(fileName);
				Scanner scanner = new Scanner(file);
				while (scanner.hasNext()) {
					String testWord = scanner.next().replaceAll("[\\pP¡®'¡°¡±]", "");
					if (!tree.contains(testWord)) {
						error += 1;
						System.out.println(testWord + " was not found in dictionary.");
					}

				}

				if (error == 0) {
					System.out.println("No spelling errors found.");
				} else {
					System.out.println(error + " spelling errors found.");
				}
				break;

			}
		}
	}
}

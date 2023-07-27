import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

public class WordCounter extends JFrame {
    private JFileChooser fileChooser;
    private JTextArea textArea;
    private JButton selectButton;
    private JLabel totalWordsLabel;
    private JLabel distinctWordsLabel;
    private JLabel stopWordsLabel;
    File file;
    String filename;
    FileReader fileReader;
    BufferedReader bufferedReader;

    private Set<String> stopWords;

    public WordCounter(){
        setTitle("Word Counter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        selectButton = new JButton("Select File");
        selectButton.addActionListener(new CountButtonListener());
        add(selectButton, BorderLayout.SOUTH);

        JPanel infoPanel = new JPanel(new GridLayout(3, 1));
        totalWordsLabel = new JLabel("Total Words: ");
        infoPanel.add(totalWordsLabel);
        distinctWordsLabel = new JLabel("Distinct Words: ");
        infoPanel.add(distinctWordsLabel);
        stopWordsLabel = new JLabel("Stop Words: ");
        infoPanel.add(stopWordsLabel);
        add(infoPanel, BorderLayout.NORTH);

        stopWords = new HashSet<>();
        stopWords.add("a");
        stopWords.add("an");
        stopWords.add("the");

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private class CountButtonListener implements ActionListener  {
        public void actionPerformed(ActionEvent e) {

            fileChooser = new JFileChooser();
            fileChooser.showOpenDialog(null);
            file = fileChooser.getSelectedFile();
            filename = file.getAbsolutePath();
            try{
                fileReader = new FileReader(filename);
                bufferedReader = new BufferedReader(fileReader);
                textArea.read(bufferedReader, null);
                bufferedReader.close();
                textArea.requestFocus();
            }catch(Exception y){
                JOptionPane.showMessageDialog(null, y);
            }
            
            String text = textArea.getText();

            // Count total words
            int totalWords = countWords(text);
            totalWordsLabel.setText("Total Words: " + totalWords);

            // Count distinct words
            Set<String> distinctWords = getDistinctWords(text);
            int numDistinctWords = distinctWords.size();
            distinctWordsLabel.setText("Distinct Words: " + numDistinctWords);

            // Remove stop words
            Set<String> filteredWords = removeStopWords(distinctWords);
            int numStopWords = numDistinctWords - filteredWords.size();
            stopWordsLabel.setText("Stop Words: " + numStopWords);
        }
    }

    private int countWords(String text) {
        String[] words = text.split("\\s+");
        return words.length;
    }

    private Set<String> getDistinctWords(String text) {
        String[] words = text.split("\\s+");
        Set<String> distinctWords = new HashSet<>(Arrays.asList(words));
        return distinctWords;
    }

    private Set<String> removeStopWords(Set<String> words) {
        Set<String> filteredWords = new HashSet<>(words);
        filteredWords.removeAll(stopWords);
        return filteredWords;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                        | UnsupportedLookAndFeelException e) {
                    e.printStackTrace();
                }
                new WordCounter();
            }
        });
    }
}

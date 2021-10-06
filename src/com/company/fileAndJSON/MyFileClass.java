package com.company.fileAndJSON;

import java.io.*;

public class MyFileClass {
    private String relativePath;
    private File relativeDirectory;
    private File relativeFile;
    private BufferedReader br = null;
    private BufferedWriter bw = null;

    public MyFileClass(String relativePath) throws IOException {
        this.relativePath=relativePath;
        if (relativePath.isEmpty()){
            relativeFile = null;
            relativeDirectory = null;
        }else {
            String[] inputs = relativePath.split("/");
            if (inputs.length == 1) {
                relativeDirectory = null;
                relativeFile = new File(relativePath);
            } else {
                relativeDirectory = new File(relativePath.split(inputs[inputs.length - 1], 0)[0]);
                relativeFile = new File(relativeDirectory, inputs[inputs.length - 1]);
            }
        }
        if (relativeDirectory!=null){
            if (!relativeDirectory.exists()){
                if (relativeDirectory.mkdirs()){
                    System.out.println("Directory was created");
                }else{
                    System.out.println("Directory was not created");
                }
            }
            System.out.println(relativeDirectory.getAbsolutePath());
        }
        if (relativeFile!=null) {
            if (!relativeFile.exists()){
                if (relativeFile.createNewFile()){
                    System.out.println("File was created");
                }else{
                    System.out.println("File was not created");
                }
            }
            System.out.println(relativeFile.getAbsolutePath());

        }
    }

    public String readBRString(){
        try {
            br = new BufferedReader(new FileReader(relativeFile));
            StringBuilder stringBuilder = new StringBuilder();

            br.lines().forEach((String s) -> stringBuilder.append(s).append("\n"));
            br.close();
            return stringBuilder.toString();
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

    public void writeIntoBW(String string, boolean append){
        try {
            bw = new BufferedWriter(new FileWriter(relativeFile, append));
            bw.write(string);
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class Tank {
	private static ArrayList<String> pathFile = new ArrayList<String>();
	private static ArrayList<String> pathSplit = new ArrayList<String>();
	private static ArrayList<String> pathHash = new ArrayList<String>();
	
	public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
		// TODO Auto-generated method stub
		
		//verifie l'existance du dossier d'entree et des elements qu'il contient
		String pathInput = args[0];
		System.out.println("Input Directory : " + pathInput );
		performPath(pathFile, pathInput);
		
		//creation du dossier part en fonction du dossier d'entrée
		File pathPart = new File(pathInput);
		String pathParent = pathPart.getParent();
		File dir = new File(pathParent+"\\"+pathPart.getName()+"Part");
		String pathOutput = dir.getPath();
		if(dir.mkdir())
		{
			System.out.println("Out Directory : " + pathOutput );
		}
		else
		{
			System.out.println("Output directory already exists");
		}
		
		// split les documents present dans le dossier
		for(int i = 0; i < pathFile.size(); i++)
		{
			fileSplitter(pathFile, i, pathOutput);
		}
		
		// hash les documents present dans le dossier
	}
	
	
	/**
	 * 
	 * @param path -> correspond au chemin du dossier des fichier originaux
	 * 		cette methode verifier l'existance du dossier des fichier orginaux
	 * 
	 */
	public static void performPath(ArrayList<String> al, String path)
	{
		File directory = new File(path);
		//verifie si le path existe
		if(directory.exists())
		{
			//verifie si le path est dossier
			if(directory.isDirectory())
			{
				System.out.println("Ce dossier contient :");
				//affiche tout les elements du dossier
				for(File file : directory.listFiles())
				{
					System.out.println(file.getAbsolutePath());
					al.add(file.getPath());
				}
				System.out.println("Ce dossier contient : " + al.size() + " fichiers" );
			}
			else
			{
				//ce n'est pas un dossier mais un fichier
				al.add(directory.getPath());
			}
		}
	}
	
	
	/**
	 * 
	 * @param file
	 * @param index
	 * @param outputDirectory
	 * @throws IOException
	 */
	public static void fileSplitter(ArrayList<String> file, int index, String outputDirectory) throws IOException
	{
		FileInputStream fis;
		FileOutputStream fos;
		int cptPart = 0;
		File f = new File(file.get(index));
		long lengthFile = f.length();
		System.out.println("Taille du fichier : " + lengthFile);
		if(lengthFile > 4096)
		{
			fis = new FileInputStream(f);
			for(int i = 0; i < (lengthFile/4096); i++)
			{
				byte[] buffer = new byte[4096];
				fis.read(buffer, 0, 4096);
				fos = new FileOutputStream(outputDirectory+"\\"+f.getName()+".part"+i);
				fos.write(buffer);
				fos.flush();
				cptPart++;
			}
			System.out.println("Le fichier a ete diviser en " + cptPart + " parties");
			performPath(pathSplit, outputDirectory);
		}
	}
	
	
	/**
	 * 
	 * @param file
	 * @throws NoSuchAlgorithmException
	 * @throws IOException
	 */
	public static void performHash(ArrayList<String> file) throws NoSuchAlgorithmException, IOException
	{
		for(int i = 0; i < pathFile.size(); i++)
		{
			byte sum[] = null;
			File f = new File(file.get(i));
			FileInputStream fis = new FileInputStream(f);
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			DigestInputStream dis = new DigestInputStream(fis, md);
			//affichage du checksum initial avant hash...
		}
	}
	
	/**
	 * @author KeviN Re fileSplitter
	 */
}

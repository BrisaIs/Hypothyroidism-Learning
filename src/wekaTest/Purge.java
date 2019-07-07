package wekaTest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;

public class Purge
{

	private String DATA_SET_PATH;
	private Instances inst_set;//Tiene las instancias a las que no les falta x atributo
	private double tolerance;

	Purge()
	{
		this("C:/Program Files/Weka-3-8/data/hypothyroid.arff");//Original
		// this("C:/Users/Unicorn/Documents/Fac/DataMining/P2/db/hypothyroid.arff");//0
		// this("C:/Users/Unicorn/Documents/Fac/DataMining/P2/db/hypothyroidB1.arff");//1
		// this("C:/Users/Unicorn/Documents/Fac/DataMining/P2/db/hypothyroidB2.arff");//17
		// this("C:/Users/Unicorn/Documents/Fac/DataMining/P2/db/hypothyroidB3.arff");//19
		// this("C:/Users/Unicorn/Documents/Fac/DataMining/P2/db/hypothyroidB4.arff");//21
		// this("C:/Users/Unicorn/Documents/Fac/DataMining/P2/db/hypothyroidB5.arff");//23
		// this("C:/Users/Unicorn/Documents/Fac/DataMining/P2/db/hypothyroidB6.arff");//25

	}

	Purge(String dSPath)
	{
		this.DATA_SET_PATH = dSPath;
		this.inst_set = IOHandler.load( dSPath );
		//this.print_data();
		this.tolerance = 10.0;
	}

	public void set_instSet(Instances inst){ this.inst_set = inst; }

	public Instances get_instSet(){ return this.inst_set; }

	public void set_path(String path){ this.DATA_SET_PATH = path; }

	public void set_tolerance(double tolerance){ this.tolerance = tolerance; }

	public Instances remove_attr(int[] attr_indexes)
	{
		//1_Setup filter
		String[] options = new String[2];
		options[0] = "-R";
		options[1] = Arrays.toString( attr_indexes ).replace(" ", "")  //remove the spaces
			    		.replace("[", "")  //remove the right bracket
			    		.replace("]", "")  //remove the left bracket
			    		.trim();           //remove trailing spaces from partially initialized arrays
		Remove remove = new Remove(); //Filter
		try
		{
			remove.setOptions(options);
			remove.setInputFormat( this.inst_set );
		}
		catch(Exception e){System.out.println(e);}
		//0_Setup filter
		//--------------------------------------------
		//1_Apply filter
		Instances newInst_set = null;
		try{ newInst_set = Filter.useFilter( this.inst_set, remove ); }
		catch(Exception e){System.out.println(e);}
		//0_Apply filter

		return newInst_set;
	}

	@SuppressWarnings("unused")
	private void print_data()
	{
		System.out.println( this.inst_set );
	}

	public void remove_inst(int index){	this.inst_set.delete(index); }
	/*
	TODO Separar en archivos diferentes todas las instancias con el mismo valor faltante

		Optener vecinos y asigmar valores aleatorios del rango
		Funcion para quitar el ruido
	*/

	/*Devuelve las instancias a las que les falta el atributo dado por index*/
	public Instances pop_subSet( int index )
	{
		Instances newInst_set =  new Instances(this.inst_set, 0);
		int c = 0;
		for(int i = 0; i < this.inst_set.numInstances(); i++)
		{
			for(int j = 0; j < this.inst_set.numAttributes(); j++)
			{
				if( Double.isNaN(this.inst_set.get(i).value(j)) && (index == j) )
				{
					newInst_set.add( this.inst_set.get(i) );
					this.inst_set.delete( i );//tiene las demás
					c++;
					break;//?
				}
			}
		}
		System.out.println(c);

		return newInst_set;
	}
}

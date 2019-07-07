package wekaTest;

import weka.core.Instances;

/*
 Ref: https://weka.wikispaces.com/Use+WEKA+in+your+Java+code
 API Doc: http://weka.sourceforge.net/doc.stable/
*/

public class Analytic
{

	public static void main(String[] args) throws Exception
	{
		// int[] nums = {27,28,30};//TBG measured y TBG el atributo clasificador
		int[] nums = {17,19,21,23,25};//Measured for A
		int action = 0;
		//data.get( 0 ).value(0) -- Return attribute value
		//data.get( 0 ).attribute(0) -- Return attribute name and type

		if( action == 0)
		{
			String data = "C:/Users/Unicorn/Documents/Fac/DataMining/P2/db/Comp/compB3.arff";
			String model = "C:/Users/Unicorn/Documents/Fac/DataMining/P2/Models/B_Skm3_2.model";
			String centroids = "C:/Users/Unicorn/Documents/Fac/DataMining/P2/Centroids/B_Skm3_2.arff";
			String stdevs = "C:/Users/Unicorn/Documents/Fac/DataMining/P2/Stdevs/B_Skm3_2.arff";

			Converter converter = new Converter(model, centroids, stdevs);
			converter.model_to_arff();

			Clustering cluster = new Clustering(data, centroids, stdevs);
			Instances complement = cluster.clusterize(true);
			IOHandler.arff_writer("C:/Users/Unicorn/Documents/Fac/DataMining/P2/db/Comp/compB3_2.arff", complement);
			cluster.count();

		}
		else
		{
			System.out.println("Ex-pectooooo Patrooonuuuum !!!");
			// Purge purifier = new Purge("C:/Users/Unicorn/Documents/Fac/DataMining/P2/db/hypothyroidA5_1.arff");
			// Purge purifier = new Purge("C:/Users/Unicorn/Documents/Fac/DataMining/P2/db/hypothyroidAF.arff");//-MeasuredA


/*Se eliminan los atributos con indices en nums*/
			//IOHandler.arff_writer("C:/Users/Unicorn/Documents/Fac/DataMining/P2/db/hypothyroid.arff", purifier.remove_attr( nums ));
			// IOHandler.arff_writer("C:/Users/Unicorn/Documents/Fac/DataMining/P2/db/hypothyroidAF_F.arff", purifier.remove_attr( nums ));//-MeasuredA


/*Se generan los subconjuntos de instancias a las que les falta x atributo*/
			// Instances temp_inst = purifier.pop_subSet( 0 );
			// Instances temp_inst = purifier.pop_subSet( 1 );
			// Instances temp_inst = purifier.pop_subSet( 17 );
			// Instances temp_inst = purifier.pop_subSet( 19 );
			// Instances temp_inst = purifier.pop_subSet( 21 );
			// Instances temp_inst = purifier.pop_subSet( 23 );
			// Instances temp_inst = IOHandler.load("C:/Users/Unicorn/Documents/Fac/DataMining/P2/db/s23.arff");
			// Instances temp_inst = purifier.pop_subSet( 25 );
			// Instances temp_inst = IOHandler.load("C:/Users/Unicorn/Documents/Fac/DataMining/P2/db/s25.arff");
			/*Pruebas*/
			// IOHandler.arff_writer("C:/Users/Unicorn/Documents/Fac/DataMining/P2/db/Try1.arff", purifier.get_instSet());
			// IOHandler.arff_writer("C:/Users/Unicorn/Documents/Fac/DataMining/P2/db/Try2.arff", temp_inst);

/*Se crean los "vecindarios" en torno al conjunto de instancias al que se le buscan los valores faltantes
			Se tomarán en cuenta vecindades de tamaño  10*/
			// Neighborhood neighborhood = new Neighborhood(purifier.get_instSet(), temp_inst, 10);

/*Se obtienen las desviaciones estándar y promedios para los vecindarios*/
			// Double[] description = neighborhood.get_description(0);
			// Double[] description = neighborhood.get_description(1);
			// Double[] description = neighborhood.get_description(17);
			// Double[] description = neighborhood.get_description(19);//Pend
			// Double[] description = neighborhood.get_description(21);
			// Double[] description = neighborhood.get_description(23);
			// Double[] description = neighborhood.get_description(25);
			// System.out.println(description[0] + "   " + description[1]);

/*Se imprimen las instancias en las que se reemplazaron los */
			// System.out.println(neighborhood.populate(description, 0).numInstances());
			//System.out.println( neighborhood.populate(description, 0) );
			// System.out.println(neighborhood.populate(description, 1).numInstances());
			// System.out.println( neighborhood.populate(description, 1) );
			// System.out.println(neighborhood.populate(description, 17).numInstances());
			// System.out.println( neighborhood.populate(description, 17) );
			// System.out.println(neighborhood.populate(description, 19).numInstances());
			// System.out.println( neighborhood.populate(description, 19) );
			// System.out.println(neighborhood.populate(description, 21).numInstances());
			// System.out.println( neighborhood.populate(description, 21) );
			// System.out.println(neighborhood.populate(description, 23).numInstances());
			// System.out.println( neighborhood.populate(description, 23) );
			// System.out.println(neighborhood.populate(description, 25).numInstances());
			// System.out.println( neighborhood.populate(description, 25) );

/*Complemento de la base de datos*/
			// System.out.println("Complemento");
			// System.out.println(purifier.get_instSet().numInstances());

/*Escrituras de pivoteo*/
			// IOHandler.arff_writer("C:/Users/Unicorn/Documents/Fac/DataMining/P2/db/hypothyroidB1.arff", purifier.get_instSet());
			// IOHandler.arff_writer("C:/Users/Unicorn/Documents/Fac/DataMining/P2/db/hypothyroidB2.arff", purifier.get_instSet());
			// IOHandler.arff_writer("C:/Users/Unicorn/Documents/Fac/DataMining/P2/db/hypothyroidB2_1.arff", purifier.get_instSet());
			// IOHandler.arff_writer("C:/Users/Unicorn/Documents/Fac/DataMining/P2/db/hypothyroidB3.arff", purifier.get_instSet());
			// IOHandler.arff_writer("C:/Users/Unicorn/Documents/Fac/DataMining/P2/db/hypothyroidB3_1.arff", purifier.get_instSet());
			// IOHandler.arff_writer("C:/Users/Unicorn/Documents/Fac/DataMining/P2/db/hypothyroidB3_2.arff", purifier.get_instSet());
			// IOHandler.arff_writer("C:/Users/Unicorn/Documents/Fac/DataMining/P2/db/hypothyroidB4.arff", purifier.get_instSet());
			// IOHandler.arff_writer("C:/Users/Unicorn/Documents/Fac/DataMining/P2/db/hypothyroidB5_1.arff", purifier.get_instSet());
			// IOHandler.arff_writer("C:/Users/Unicorn/Documents/Fac/DataMining/P2/db/hypothyroidB6.arff", purifier.get_instSet());
			// IOHandler.arff_writer("C:/Users/Unicorn/Documents/Fac/DataMining/P2/db/hypothyroidB7.arff", purifier.get_instSet());
			// IOHandler.arff_writer("C:/Users/Unicorn/Documents/Fac/DataMining/P2/db/hypothyroidB8.arff", purifier.get_instSet());



		}
	}

}

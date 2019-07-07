package wekaTest;
import weka.core.Instances;

class Clustering
{
	private static final int FRONTIER = -1;
	private static final int NOISE = -2;

	private Instances dataInst, centroidInst, sdevInst;
	private int clustersAmount, attrAmount, dataSize;

	private int[][] comparissonResults;//Aqui se guardan los resultados de las comparaciones de cada instancia con cada cluster.
	private int[] classification;//Aqui se guardan los resultados de la clasificacion en clusters de cada instancia.
	private int[] withoutC;

	Clustering()
	{
		this(
						"C:/Users/Unicorn/Documents/Fac/DataMining/P2/db/hypothyroidAF_numeric.arff",
						"C:/Users/Unicorn/Documents/Fac/DataMining/P2/Centroids/A_base2.arff",
						"C:/Users/Unicorn/Documents/Fac/DataMining/P2/Stdevs/A_base2.arff");
	}

	Clustering(String data_path, String centroids_path, String sdev_path)
	{
		this.dataInst = IOHandler.load(data_path);
		this.centroidInst = IOHandler.load(centroids_path);
		this.sdevInst = IOHandler.load(sdev_path);

		this.clustersAmount = this.centroidInst.numInstances();
		this.attrAmount = this.dataInst.numAttributes();
		this.dataSize = this.dataInst.numInstances();

		this.comparissonResults = new int [clustersAmount][attrAmount];
		this.classification = new int [dataSize];
		this.withoutC = new int[dataSize];
	}

	//Imprime los .arff segun el entero que se le pasa
	public void printFile(int index)
	{
		switch(index)
		{
			case 0:
				System.out.println("\nDataset:\n");
				System.out.println( this.dataInst );
				break;
			case 1:
				System.out.println("\nCentroids:\n");
				System.out.println( this.centroidInst );
				break;
			case 2:
				System.out.println("\nStandard Deviations:\n");
				System.out.println( this.sdevInst );
				break;
		}
	}

	/*
		Hace las comparaciones de las instancias con los datatos de cada cluster.
		Almacena los resultados de las comparaciones en comparissonResults.
		Llama a compare, para determinar en que cluster se encuentra, si es ruido o est� en frontera
	*/
	public Instances clusterize( boolean consider_noise)
	{
		double data, centroid, sdev = 0;
		double extI, extD = 0;
		int result;

		Instances wasteInst = new Instances(this.dataInst, 0);

		for(int k = 0; k < this.dataSize; k ++)
		{
			for(int i = 0; i < this.clustersAmount; i++)
				for(int j = 0; j < this.attrAmount ; j++)
				{
					data = this.dataInst.get(k).value(j);
					centroid = this.centroidInst.get(i).value(j);
					sdev = this.sdevInst.get(i).value(j);

					extI = centroid - sdev;
					extD = centroid + sdev;

					/*Para comprobar la Condici�n de pertenencia*/
					if( (data >= extI) && (data <=  extD) )//Condici�n de pertenencia
						this.comparissonResults[i][j] = 1;
					else
						this.comparissonResults[i][j] = 0;
				}

			result = compare();//obtiene clasificaci�n
			this.classification[k] = result;//almacena el resultado

			/*Si la instancia no se clasifica en ning�n cluster se imprime para ser considerado en la
			siguiente corrida jerarqu�ca.*/
			if( result == FRONTIER )
				wasteInst.add( this.dataInst.get(k) );
			else if( (result == NOISE) && consider_noise )
				wasteInst.add( this.dataInst.get(k) );
		}

		return wasteInst;
	}

	/*
		Lee los resultados que hay en comparissonResults (deber�a hacerlo una vez por cluster, se reutiliza la estructura)
		Se determina la clasificaci�n de la instancia
	*/
	public int compare()
	{
		int[] instInCluster = new int[ this.clustersAmount ];

		for( int i = 0; i < this.clustersAmount; i++)
			for(int j = 0; j < this.attrAmount ; j++)
				if( this.comparissonResults[i][j] == 1 )
					instInCluster[i]++;

		int frontier_coincidence = 0;
		boolean frontier_flag = true;
		for(int i = 0; i < this.clustersAmount-1; i++)
			if( frontier_flag = instInCluster[i] ==	this.attrAmount )
				for(int j = i+1; j < this.clustersAmount; j++)
					if(frontier_flag && (instInCluster[j] ==	this.attrAmount))
						frontier_coincidence++;

		int in_cluster = 0;
		boolean noisy_flag = true;
		if(frontier_coincidence == 0)
		{
			for(int i = 0; i < this.clustersAmount; i++)
				noisy_flag = noisy_flag && (instInCluster[i] < this.attrAmount);

			if( !noisy_flag )
				for(int i = 0; i < this.clustersAmount; i++)
					if(instInCluster[i] == this.attrAmount)
					{
						in_cluster = i;
						break;
					}
		}

		int result;
		if(frontier_coincidence > 0)
			result = FRONTIER;
		else if( noisy_flag )
			result = NOISE;
		else
			result = in_cluster; //cluster

		return result;

	}

	/*Cuenta las  instancias en cada cluster, frontera y ruido*/
	public void count()
	{
		int[] counter = new int[this.clustersAmount];
		int noise = 0;
		int infrontier = 0;

		for(int i = 0; i < this.dataSize; i++)
		{
			if( this.classification[i] >= 0 )
				counter[ this.classification[i] ]++;
			else
				switch(this.classification[i])
				{
					case NOISE:
						noise++;
						break;
					case FRONTIER:
						infrontier++;
						break;
				}
		}

		for(int i = 0; i < this.clustersAmount; i++)
			System.out.println("Cluster "+i+" = "+counter[i]);
		System.out.println("Noise = " + noise + "\nIn frontier = " + infrontier +  "\n");

	}
}

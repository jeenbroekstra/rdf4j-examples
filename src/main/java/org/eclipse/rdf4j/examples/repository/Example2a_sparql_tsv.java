/******************************************************************************* 
 * Copyright (c) 2020 Eclipse RDF4J contributors.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Distribution License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *******************************************************************************/
package org.eclipse.rdf4j.examples.repository;

import java.net.URL;

import org.eclipse.rdf4j.query.TupleQuery;
import org.eclipse.rdf4j.query.resultio.text.tsv.SPARQLResultsTSVWriter;
import org.eclipse.rdf4j.repository.Repository;
import org.eclipse.rdf4j.repository.RepositoryConnection;
import org.eclipse.rdf4j.repository.sail.SailRepository;
import org.eclipse.rdf4j.rio.RDFFormat;
import org.eclipse.rdf4j.sail.memory.MemoryStore;

/**
 * Adding data to a {@link Repository}: a simple SPARQL query, output to tab-separated values
 * 
 */
public class Example2a_sparql_tsv {

	public static void main(String[] args) throws Exception {

		URL data = Example2a_sparql_tsv.class.getResource("/example-data-artists.ttl");

		Repository rep = new SailRepository(new MemoryStore());
		try (RepositoryConnection conn = rep.getConnection()) {
			conn.add(data.openStream(), data.toExternalForm(), RDFFormat.TURTLE);

			TupleQuery query = conn.prepareTupleQuery("SELECT * { ?s ?p ?o } LIMIT 100");
			query.evaluate(new SPARQLResultsTSVWriter(System.out));
		} finally {
			rep.shutDown();
		}
	}

}

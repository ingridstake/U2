package com.U2.backend.Search;

import com.U2.backend.APIDataService;
import com.U2.backend.DataObjectContracts.IEvent;
import com.U2.backend.DataObjectContracts.IVenue;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.ByteBuffersDirectory;
import org.apache.lucene.store.Directory;
import org.json.JSONException;

import java.io.IOException;
import java.util.List;

public class Search {

    private static List<IEvent> events;


    public static void main(String[] args) throws IOException, ParseException {
        StandardAnalyzer analyzer = new StandardAnalyzer();
        Directory index = new ByteBuffersDirectory();

        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        IndexWriter w = new IndexWriter(index, config);

        var dataService = new APIDataService();
        events = dataService.getEvents();


        addDoc(w, events.get(0).getName(), events.get(0).getId());
        addDoc(w, events.get(1).getName(), events.get(1).getId());
        addDoc(w, events.get(2).getName(), events.get(2).getId());
        addDoc(w, events.get(3).getName(), events.get(3).getId());
        w.close();

        //String querystr = args.length > 0 ? args[0] : "lucene";
        //Query q = new QueryParser("title", analyzer).parse(querystr);

        String querystr2 = args.length > 0 ? args[0] : "b*";
        Query q2 = new QueryParser("name", analyzer).parse(querystr2);

        int hitsPerPage = 10;
        IndexReader reader = DirectoryReader.open(index);
        IndexSearcher searcher = new IndexSearcher(reader);
        TopDocs docs = searcher.search(q2, hitsPerPage);
        ScoreDoc[] hits = docs.scoreDocs;


        System.out.println("Found " + hits.length + " hits.");

        for(int i=0;i<hits.length;++i) {
            int docId = hits[i].doc;
            Document d = searcher.doc(docId);
            System.out.println((i + 1) + ". " + d.get("id") + "\t" + d.get("name"));
        }
        reader.close();
    }

    public static void addDoc(IndexWriter w, String name, String id) throws IOException {
        Document doc = new Document();
        doc.add(new TextField("name", name, Field.Store.YES));
        doc.add(new StringField("id", id, Field.Store.YES));
        w.addDocument(doc);
    }


    /*
    //RAMDirectory ramDirectory = new RAMDirectory();
    //Directory memoryIndex = new RAMDirectory();
    StandardAnalyzer analyzer = new StandardAnalyzer();
    IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
    IndexWriter writter = new IndexWriter(memoryIndex, indexWriterConfig);
    Document document = new Document();

    document.add()
    document.add(new TextField("title",title, Field.Store.YES));
    document.add(new TextField("body", body, Field.Store.YES));

    writter.addDocument(document);
    writter.close();

     */
}

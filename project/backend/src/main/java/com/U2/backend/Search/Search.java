package com.U2.backend.Search;

import com.U2.backend.APIDataService;
import com.U2.backend.DataObjectContracts.IEvent;
import com.U2.backend.DataObjectContracts.IVenue;
import com.U2.backend.DataObjectFactories.DataObjectFactory;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.*;
import org.apache.lucene.queries.mlt.MoreLikeThis;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.ByteBuffersDirectory;
import org.apache.lucene.store.Directory;
import org.apache.lucene.util.PagedBytes;
import org.apache.lucene.util.packed.PackedInts;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Search {

    public static String performSearch(List<IEvent> events, String searchParam) {
        StandardAnalyzer analyzer = new StandardAnalyzer();
        Directory index = new ByteBuffersDirectory();

        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        IndexWriter w = null;
        try {
            w = new IndexWriter(index, config);


        List<IEvent> hundredEvents = events.subList(0,100);

        for (IEvent event : hundredEvents) {
            addDoc(w, event.getId());
        }
        w.close();

        Query query = new QueryParser("name", analyzer).parse(searchParam + "*");


        int hitsPerPage = 100;
        IndexReader reader = DirectoryReader.open(index);
        IndexSearcher searcher = new IndexSearcher(reader);
        TopDocs docs = searcher.search(query, hitsPerPage);
        ScoreDoc[] hits = docs.scoreDocs;

        //System.out.println("Found " + hits.length + " hits.");

        List<String> hitsId = new ArrayList<>();
        for(int i=0;i<hits.length;++i) {
            int docId = hits[i].doc;
            Document d = searcher.doc(docId);
            hitsId.add(d.get("id"));
        }
        var eventHits = events.stream().filter(e -> hitsId.contains(e.getId())).toList();
        DataObjectFactory.convertToJSONString(eventHits);

        reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void addDoc(IndexWriter w, String id) throws IOException {
        Document doc = new Document();
        //doc.add(new TextField("name", name, Field.Store.YES));
        doc.add(new TextField("id", id, Field.Store.YES));
        //doc.add(new TextField("description", description, Field.Store.YES));
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

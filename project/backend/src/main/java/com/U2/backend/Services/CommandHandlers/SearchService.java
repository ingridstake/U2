package com.U2.backend.Services.CommandHandlers;

import com.U2.backend.Data.DataObjectContracts.IEvent;
import com.U2.backend.Data.DataObjectFactory;
import com.U2.backend.Services.Contracts.ISearchService;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.ByteBuffersDirectory;
import org.apache.lucene.store.Directory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A search service.
 */
public class SearchService implements ISearchService {

    private List<IEvent> events;

    public SearchService(List<IEvent> events){
        this.events = events;
    }

    /**
     * Search for matching events with the parameter sent from frontend.
     * Search with help of Lucene, and analyze all events.
     * The search parameter is parsed with the query '*', which search for every name/description
     * that starts with the parameter.
     * @param searchParam is the search word being sent from frontend.
     * @return a string of all the matching events.
     */
    public String performSearch(String searchParam) {
        if (searchParam.equals("")){
            return null;
        }

        StandardAnalyzer analyzer = new StandardAnalyzer();
        Directory index = new ByteBuffersDirectory();

        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        IndexWriter w = null;
        try {
            w = new IndexWriter(index, config);

        List<IEvent> events = this.events;

        for (IEvent event : events) {
            addDoc(w, event.getName(), event.getId(), event.getDescription());
        }
        w.close();

        Query query = new QueryParser("name", analyzer).parse(searchParam + '*');

        int hitsPerPage = 100;
        IndexReader reader = DirectoryReader.open(index);
        IndexSearcher searcher = new IndexSearcher(reader);
        TopDocs docs = searcher.search(query, hitsPerPage);
        ScoreDoc[] hits = docs.scoreDocs;

        List<String> hitsId = new ArrayList<>();
        for(int i=0;i<hits.length;++i) {
            int docId = hits[i].doc;
            Document d = searcher.doc(docId);
            hitsId.add(d.get("id"));
        }
        var eventHits = this.events.stream().filter(e -> hitsId.contains(e.getId())).toList();

        reader.close();
        return DataObjectFactory.convertToJSONString(eventHits);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Adds the events to type Document, to be able to perform Lucene/index.
     * @param w is the IndexWriter
     * @param name is the name of the event
     * @param id is the id of the event
     * @param description is the description of the event
     * @throws IOException
     */
    private void addDoc(IndexWriter w, String name, String id, String description) throws IOException {
        Document doc = new Document();
        doc.add(new TextField("name", name, Field.Store.YES));
        doc.add(new TextField("id", id, Field.Store.YES));
        doc.add(new TextField("description", description, Field.Store.YES));
        w.addDocument(doc);
    }
}

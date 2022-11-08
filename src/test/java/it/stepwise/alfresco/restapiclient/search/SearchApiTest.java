package it.stepwise.alfresco.restapiclient.search;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import it.stepwise.alfresco.restapiclient.AlfrescoRestApi;
import it.stepwise.alfresco.restapiclient.search.querybuilder.Fts;
import it.stepwise.alfresco.restapiclient.search.searchparams.Fields;
import it.stepwise.alfresco.restapiclient.search.searchparams.Include;
import it.stepwise.alfresco.restapiclient.search.searchparams.Language;
import it.stepwise.alfresco.restapiclient.search.searchparams.Paging;
import it.stepwise.alfresco.restapiclient.search.searchparams.Query;
import it.stepwise.alfresco.restapiclient.search.searchparams.Sort;
import it.stepwise.alfresco.restapiclient.util.Host;
import it.stepwise.alfresco.restapiclient.util.ResponseEither;

public class SearchApiTest {

    private Host host = new Host("http", "localhost", 8080);
    private AlfrescoRestApi alfrescoRestApi = new AlfrescoRestApi(this.host, "admin", "admin");
    private SearchApi searchApi = new SearchApi(this.alfrescoRestApi);

    @Test
    public void t1_searchAFTSBasic() {

        SearchBody searchBody = new SearchBody();
        Query query = new Query("TYPE:\"cm:content\"");
        searchBody.setQuery(query);

        ResponseEither<it.stepwise.alfresco.restapiclient.util.Error, JSONObject> responseEither = this.searchApi.search(
            searchBody);

        System.out.println(responseEither.getData());

        assertNull(responseEither.getError());
        assertTrue(responseEither.getData().getJSONObject("list") instanceof JSONObject);
        assertTrue(responseEither.getData().getJSONObject("list").getJSONArray("entries") instanceof JSONArray);
    }

    @Test
    public void t2_checkSearchModel() {

        assertEquals("afts", Language.AFTS.getValue());
        assertEquals("lucene", Language.LUCENE.getValue());
        assertEquals("cmis", Language.CMIS.getValue());

        assertEquals("allowableOperations", Include.ALLOWABLE_OPERATIONS.getValue());
        assertEquals("aspectNames", Include.ASPECT_NAMES.getValue());
        assertEquals("isLink", Include.IS_LINK.getValue());
        assertEquals("path", Include.PATH.getValue());
        assertEquals("properties", Include.PROPERTIES.getValue());
        assertEquals("association", Include.ASSOCIATION.getValue());

        assertEquals("id", Fields.ID.getValue());
        assertEquals("name", Fields.NAME.getValue());
        assertEquals("search", Fields.SEARCH.getValue());

        assertEquals("FIELD", Sort.Type.FIELD.getValue());
        assertEquals("DOCUMENT", Sort.Type.DOCUMENT.getValue());
        assertEquals("SCORE", Sort.Type.SCORE.getValue());

    }

    @Test
    public void t3_checkSearchBodyStructure() {

        SearchBody searchBody = new SearchBody();
        Query query = new Query(Language.AFTS, "TYPE:\"cm:content\"");
        searchBody.setQuery(query);
        Include[] include = new Include[] {Include.IS_LINK};
        searchBody.setInclude(include);
        Sort[] sort = new Sort[] {new Sort("cm:name")};
        searchBody.setSort(sort);
        Paging paging = new Paging(10, 5);
        searchBody.setPaging(paging);
        Fields[] fields = new Fields[] {Fields.NAME};
        searchBody.setFields(fields);

        assertEquals("TYPE:\"cm:content\"", searchBody.getQuery().getQuery());
        assertEquals("afts", searchBody.getQuery().getLanguage().getValue());
        assertEquals(1, searchBody.getInclude().length);
        assertEquals(1, searchBody.getSort().length);
        assertEquals("cm:name", searchBody.getSort()[0].getField());
        assertEquals("FIELD", searchBody.getSort()[0].getType().getValue());
        assertTrue(searchBody.getSort()[0].getAscending());
        assertEquals(10, searchBody.getPaging().getMaxItems());
        assertEquals(5, searchBody.getPaging().getSkipCount());
        assertEquals(1, searchBody.getFields().length);
        assertEquals("name", searchBody.getFields()[0].getValue());

    }

    @Test
    public void t4_checkFTSQueryBuilder() {

        SearchBody searchBody = new SearchBody();
        Fts fts = Fts.ftsBuilder()
            .EXACTTYPE("cm:content")
            .AND()
            .NOT_PROP("cm:title", "test.docx")
            .AND()
            .ASPECT("cm:titled");

        Query query = new Query(fts.getQuery());
        searchBody.setQuery(query);

        assertEquals("EXACTTYPE:\"cm:content\" AND !cm:title:\"test.docx\" AND ASPECT:\"cm:titled\"", searchBody.getQuery().getQuery());

    }

    @Test
    public void t5_searchAFTSComplex() {

        SearchBody searchBody = new SearchBody();
        Query query = new Query("TYPE:\"cm:content\"");
        searchBody.setQuery(query);
        Include[] include = new Include[] {Include.IS_LINK};
        searchBody.setInclude(include);
        Sort[] sort = new Sort[] {new Sort("cm:title")};
        searchBody.setSort(sort);
        Paging paging = new Paging(10, 5);
        searchBody.setPaging(paging);

        ResponseEither<it.stepwise.alfresco.restapiclient.util.Error, JSONObject> responseEither = this.searchApi.search(
            searchBody);

        assertNull(responseEither.getError());

    }

    @Test
    public void t6_searchLucene() {

        SearchBody searchBody = new SearchBody();
        Query query = new Query(Language.LUCENE, "cm\\:name:\"Test.docx\"");
        searchBody.setQuery(query);
        Include[] include = new Include[] {Include.IS_LINK, Include.ALLOWABLE_OPERATIONS, Include.PATH};
        searchBody.setInclude(include);
        Sort[] sort = new Sort[] {new Sort("cm:name")};
        searchBody.setSort(sort);
        Paging paging = new Paging(10, 5);
        searchBody.setPaging(paging);

        ResponseEither<it.stepwise.alfresco.restapiclient.util.Error, JSONObject> responseEither = this.searchApi.search(
            searchBody);

        assertNull(responseEither.getError());

    }

    @Test
    public void t7_searchCmis() {

        SearchBody searchBody = new SearchBody();
        Query query = new Query(Language.CMIS, "select * from cmis:document where cmis:name='Test.docx'");
        searchBody.setQuery(query);

        ResponseEither<it.stepwise.alfresco.restapiclient.util.Error, JSONObject> responseEither = this.searchApi.search(
            searchBody);

        assertNull(responseEither.getError());

    }
    
}

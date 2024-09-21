package com.batiCuisine.DAO.Interfaces;

import com.batiCuisine.Models.QuoteModel;

public interface QuoteDAO {
    void insertQuote(QuoteModel quote , String savedQuotes) throws Exception;
}

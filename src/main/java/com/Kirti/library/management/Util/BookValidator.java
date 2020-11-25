package com.Kirti.library.management.Util;


import com.Kirti.library.management.DataAccessLayer.Book;

public class BookValidator {
    public boolean isValid(Book book){
        if(book.getTitle()=="" || book.getTitle()==null)
            return false;
        return true;
    }
}

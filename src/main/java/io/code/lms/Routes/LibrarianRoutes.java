package io.code.lms.Routes;

public interface LibrarianRoutes {
    String librarianBaseUrl="librarian/";
    String librarianBookCrud= LibrarianRoutes.librarianBaseUrl + "book/";
    String librarianScholarCrud= LibrarianRoutes.librarianBaseUrl +"scholar/";
    String issueBook= LibrarianRoutes.librarianBaseUrl + LibrarianRoutes.librarianBookCrud + "/issue";
    String renewBook= LibrarianRoutes.librarianBaseUrl + LibrarianRoutes.librarianBookCrud + "/renew";
    String reserveBook= LibrarianRoutes.librarianBaseUrl + LibrarianRoutes.librarianBookCrud + "/reserve";
    String scholarFine= LibrarianRoutes.librarianBaseUrl + LibrarianRoutes.librarianScholarCrud + "/fine";
}

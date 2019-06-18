package io.code.lms.Routes;

public interface ScholarRoutes {
    String scholarBaseUrl="/scholar";
    String addBookScholar=ScholarRoutes.scholarBaseUrl + "/addBook";
    String returnBookScholar=ScholarRoutes.scholarBaseUrl + "/returnBook";
    String payFine=ScholarRoutes.scholarBaseUrl + "/fine";
    String renewBook=ScholarRoutes.scholarBaseUrl + "/book/renew";
}

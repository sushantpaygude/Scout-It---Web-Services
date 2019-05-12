CREATE TABLE public.users
(
    userid serial PRIMARY KEY,
    firstName character varying(300),
    lastName character varying(300),
    email character varying(500)
)

CREATE TABLE public.products
(
    productid serial PRIMARY KEY,
    userid integer,
    productname character varying,
    productdescription character varying,
    productcost integer,
    productdate bigint
)

CREATE TABLE public.courses
(
    coursename character varying,
    courseid serial PRIMARY KEY,
    status character varying,
)



CREATE TABLE public.usercourses
(
    usercourseid serial PRIMARY KEY,
    courseid integer,
    userid integer,
    CONSTRAINT instructor FOREIGN KEY (userid)
        REFERENCES public.users (userid),
    CONSTRAINT course FOREIGN KEY (courseid)
        REFERENCES public.courses (courseid)
)

CREATE TABLE public.marketplace
(
    marketplaceid serial PRIMARY KEY,
    usercourseid integer,
    productid integer,
    postdate bigint,
    productpurchased boolean
)


CREATE TABLE public.marketplacecourse
(
    marketplacecourseid serial PRIMARY KEY,
    usercourseid integer,
	CONSTRAINT usercourses_fk FOREIGN KEY (usercourseid)
        REFERENCES public.usercourses (usercourseid)
)


CREATE TABLE public.marketplaceproducts
(
    marketplaceproductsid serial PRIMARY KEY,
    productid integer,
    postdate bigint,
    productpurchased character varying,
	CONSTRAINT userproducts_fk FOREIGN KEY (productid)
        REFERENCES public.products (productid)
)

CREATE TABLE public.purchaseproducts
(
    purchaseid serial PRIMARY KEY,
    marketplaceid integer,
    userid integer,
	CONSTRAINT marketplace_fk FOREIGN KEY (marketplaceid)
	REFERENCES public.marketplaceproducts,
	CONSTRAINT user_fk FOREIGN KEY (userid)
	REFERENCES public.users
)

DROP TABLE tb_consumidor CASCADE CONSTRAINTS;
DROP TABLE tb_feedback CASCADE CONSTRAINTS;
DROP TABLE tb_feedback_meio CASCADE CONSTRAINTS;
DROP TABLE tb_fornecedor CASCADE CONSTRAINTS;

commit;

CREATE TABLE tb_consumidor (
    id_consumidor   NUMBER(10) NOT NULL,
    nome_consumidor VARCHAR2(25),
    nome_usuario    VARCHAR2(25) NOT NULL
);

ALTER TABLE tb_consumidor ADD CONSTRAINT consumidor_pk PRIMARY KEY ( id_consumidor );

commit;

CREATE TABLE tb_feedback (
    id_feedback      NUMBER(10) NOT NULL,
    feedback         VARCHAR2(600) NOT NULL,
    data_feedback    DATE DEFAULT SYSDATE NOT NULL,
    post             VARCHAR2(600),
    id_consumidor    NUMBER(10) NOT NULL,
    id_feedback_meio NUMBER(10) NOT NULL
);

ALTER TABLE tb_feedback ADD CONSTRAINT feedback_pk PRIMARY KEY ( id_feedback );

commit;

CREATE TABLE tb_feedback_meio (
    id_feedback_meio NUMBER(10) NOT NULL,
    nome_meio        VARCHAR2(15) NOT NULL
);

ALTER TABLE tb_feedback_meio ADD CONSTRAINT feedback_meio_pk PRIMARY KEY ( id_feedback_meio );

commit;

CREATE TABLE tb_fornecedor (
    id_fornecedor   NUMBER(10) NOT NULL,
    nome_fornecedor VARCHAR2(25) NOT NULL,
    telefone        VARCHAR2(20),
    email           VARCHAR2(50) NOT NULL,
    cnpj             VARCHAR2(25) NOT NULL
);

ALTER TABLE tb_fornecedor ADD CONSTRAINT fornecedor_pk PRIMARY KEY ( id_fornecedor );

ALTER TABLE tb_fornecedor ADD CONSTRAINT fornecedor__un UNIQUE ( email,
                                                                 cnpj );

commit;

ALTER TABLE tb_feedback
    ADD CONSTRAINT feedback_consumidor_fk FOREIGN KEY ( id_consumidor )
        REFERENCES tb_consumidor ( id_consumidor );

commit;

ALTER TABLE tb_feedback
    ADD CONSTRAINT feedback_meio_fk FOREIGN KEY ( id_feedback_meio )
        REFERENCES tb_feedback_meio ( id_feedback_meio );

commit;

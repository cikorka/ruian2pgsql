DO $$
    BEGIN
        BEGIN
            ALTER TABLE rn_orp ADD COLUMN okres_kod integer;
        EXCEPTION
            WHEN duplicate_column THEN RAISE NOTICE 'Column okres_kod already exists in rn_orp';
        END;
    END;
$$;
DO $$
    BEGIN
        BEGIN
            CREATE INDEX rn_orp_okres_kod_idx ON rn_orp (okres_kod);
        EXCEPTION
            WHEN duplicate_table THEN RAISE NOTICE 'Index rn_orp_okres_kod_idx already exists.';
        END;
    END;
$$;
DO $$
    BEGIN
        BEGIN
            ALTER TABLE hlavicka ADD COLUMN verze_vfr varchar;
        EXCEPTION
            WHEN duplicate_column THEN RAISE NOTICE 'Column okres_kod verze_vfr exists in hlavicka';
        END;
    END;
$$;
DO $$
    BEGIN
        BEGIN
            ALTER TABLE hlavicka ADD COLUMN platnost_dat_k_usui timestamp without time zone;
        EXCEPTION
            WHEN duplicate_column THEN RAISE NOTICE 'Column okres_kod platnost_dat_k_usui exists in hlavicka';
        END;
    END;
$$;
DO $$
    BEGIN
        BEGIN
            ALTER TABLE hlavicka ADD COLUMN platnost_dat_k_iskn timestamp without time zone;
        EXCEPTION
            WHEN duplicate_column THEN RAISE NOTICE 'Column okres_kod platnost_dat_k_iskn exists in hlavicka';
        END;
    END;
$$;

DO $$
    BEGIN
        BEGIN
            ALTER TABLE rn_stat ADD COLUMN datum_vzniku date;
        EXCEPTION
            WHEN duplicate_column THEN RAISE NOTICE 'Column rn_stat datum_vzniku exists in hlavicka';
        END;
    END;
$$;

DO $$
    BEGIN
        BEGIN
            ALTER TABLE rn_region_soudrznosti ADD COLUMN datum_vzniku date;
        EXCEPTION
            WHEN duplicate_column THEN RAISE NOTICE 'Column rn_region_soudrznosti datum_vzniku exists in hlavicka';
        END;
    END;
$$;

DO $$
    BEGIN
        BEGIN
            ALTER TABLE rn_vusc ADD COLUMN datum_vzniku date;
        EXCEPTION
            WHEN duplicate_column THEN RAISE NOTICE 'Column rn_vusc datum_vzniku exists in hlavicka';
        END;
    END;
$$;

DO $$
    BEGIN
        BEGIN
            ALTER TABLE rn_kraj_1960 ADD COLUMN datum_vzniku date;
        EXCEPTION
            WHEN duplicate_column THEN RAISE NOTICE 'Column rn_kraj_1960 datum_vzniku exists in hlavicka';
        END;
    END;
$$;

DO $$
    BEGIN
        BEGIN
            ALTER TABLE rn_okres ADD COLUMN datum_vzniku date;
        EXCEPTION
            WHEN duplicate_column THEN RAISE NOTICE 'Column rn_okres datum_vzniku exists in hlavicka';
        END;
    END;
$$;

DO $$
    BEGIN
        BEGIN
            ALTER TABLE rn_orp ADD COLUMN datum_vzniku date;
        EXCEPTION
            WHEN duplicate_column THEN RAISE NOTICE 'Column rn_orp datum_vzniku exists in hlavicka';
        END;
    END;
$$;

DO $$
    BEGIN
        BEGIN
            ALTER TABLE rn_pou ADD COLUMN datum_vzniku date;
        EXCEPTION
            WHEN duplicate_column THEN RAISE NOTICE 'Column rn_pou datum_vzniku exists in hlavicka';
        END;
    END;
$$;

DO $$
    BEGIN
        BEGIN
            ALTER TABLE rn_obec ADD COLUMN datum_vzniku date;
        EXCEPTION
            WHEN duplicate_column THEN RAISE NOTICE 'Column rn_obec datum_vzniku exists in hlavicka';
        END;
    END;
$$;

DO $$
    BEGIN
        BEGIN
            ALTER TABLE rn_cast_obce ADD COLUMN datum_vzniku date;
        EXCEPTION
            WHEN duplicate_column THEN RAISE NOTICE 'Column rn_cast_obce datum_vzniku exists in hlavicka';
        END;
    END;
$$;

DO $$
    BEGIN
        BEGIN
            ALTER TABLE rn_mop ADD COLUMN datum_vzniku date;
        EXCEPTION
            WHEN duplicate_column THEN RAISE NOTICE 'Column rn_mop datum_vzniku exists in hlavicka';
        END;
    END;
$$;

DO $$
    BEGIN
        BEGIN
            ALTER TABLE rn_momc ADD COLUMN datum_vzniku date;
        EXCEPTION
            WHEN duplicate_column THEN RAISE NOTICE 'Column rn_momc datum_vzniku exists in hlavicka';
        END;
    END;
$$;

DO $$
    BEGIN
        BEGIN
            ALTER TABLE rn_katastralni_uzemi ADD COLUMN datum_vzniku date;
        EXCEPTION
            WHEN duplicate_column THEN RAISE NOTICE 'Column rn_katastralni_uzemi datum_vzniku exists in hlavicka';
        END;
    END;
$$;

DO $$
    BEGIN
        BEGIN
            ALTER TABLE rn_zsj ADD COLUMN datum_vzniku date;
        EXCEPTION
            WHEN duplicate_column THEN RAISE NOTICE 'Column rn_zsj datum_vzniku exists in hlavicka';
        END;
    END;
$$;
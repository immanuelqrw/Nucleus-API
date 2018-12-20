"""
Removes old databases and then creates databases
"""
import psycopg2 as psy

from config import (
    CREATE_DATABASE_FILES,
    CREATE_TABLES_FILES,
    DATABASE_CONNECTION,
    DATABASE_TABLES,
    DROP_DATABASE_FILES,
    FAKE_DATA_FILES,
    SEED_DATA_FILES
)


if __name__ == "__main__":
    with psy.connect(**DATABASE_CONNECTION) as connection:
        with connection.cursor() as cursor:
            for DROP_DATABASE_FILE in DROP_DATABASE_FILES:
                with DROP_DATABASE_FILE.open(mode="r") as ddf:
                    try:
                        cursor.execute(ddf.read())
                    except Exception as e:
                        continue
            for CREATE_DATABASE_FILE in CREATE_DATABASE_FILES:
                with CREATE_DATABASE_FILE.open(mode="r") as cdf:
                    cursor.execute(cdf.read())
"""
Drops databases
"""
import psycopg2 as psy

from config import (
    DATABASE_CONNECTION,
    DROP_DATABASE_FILES,
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

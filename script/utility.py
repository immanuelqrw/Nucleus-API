from pathlib import Path

ROOT_DIR: Path = Path(".").absolute
SCRIPT_DIR: Path = ROOT_DIR.joinpath("script")
SQL_DIR: Path = SCRIPT_DIR.joinpath("sql")


def to_camelcase(word):
    return f"{word[0].lower()}{word[1:]}"

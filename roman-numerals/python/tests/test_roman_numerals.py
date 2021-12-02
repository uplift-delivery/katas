import unittest
from bowling_game.bowling_game import something


class MyTestCase(unittest.TestCase):
    def test_something(self):
        output = something()
        self.assertTrue(output)  # add assertion here

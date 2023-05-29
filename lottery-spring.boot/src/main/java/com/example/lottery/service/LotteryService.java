package com.example.lottery.service;

import java.util.List;

public interface LotteryService {
	public List<Integer> draw(int max,int size);
	public List<List<Integer>> draw(int max,int size,int column);
}

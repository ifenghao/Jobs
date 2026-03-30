import numpy as np
 
# N是批量大小; D_in是输入维度;
# H是隐藏的维度; D_out是输出维度。
N, D_in, H, D_out = 64, 1000, 100, 10
 
# 创建随机输入和输出数据
x = np.random.randn(N, D_in)  # 64 * 1000
y = np.random.randn(N, D_out)  # 64 * 10
 
# 随机初始化两层网络权重
w1 = np.random.randn(D_in, H)  # 1000 * 100
w2 = np.random.randn(H, D_out)  # 100 * 10
 
learning_rate = 1e-6
for t in range(500):
    # 前向传递：计算预测值y
    h = x.dot(w1)  # 64 * 100
    h_relu = np.maximum(h, 0)  # 64 * 100
    y_pred = h_relu.dot(w2)  # 64 * 10
 
    # 计算和打印平方损失loss
    loss = np.square(y_pred - y).sum()
    print(t, loss)
 
    # 反向传播，计算w1和w2对loss的梯度：向量对标量求导的结果的维度和向量的维度是一致的
    grad_y_pred = 2.0 * (y_pred - y)  # 64 * 10
    grad_w2 = h_relu.T.dot(grad_y_pred)  # 100 * 10 = (64 * 100).T.dot(64 * 10)

    grad_h_relu = grad_y_pred.dot(w2.T)  # 64 * 100 = (64 * 10).dot(100 * 10).T
    grad_h = grad_h_relu.copy()
    grad_h[h < 0] = 0  # relu负数部分梯度为0
    grad_w1 = x.T.dot(grad_h)  # 1000 * 100 = (64 * 1000).T.dot(64 * 100)
 
    # 更新权重
    w1 -= learning_rate * grad_w1
    w2 -= learning_rate * grad_w2